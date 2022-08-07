package com.inventory.config;

import com.inventory.batch.JobCompletionNotificationListener;
import com.inventory.batch.ProductDataProcessor;
import com.inventory.dto.ProductInput;
import com.inventory.entity.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] FIELD_NAME = new String[]{"code", "name", "batch", "stock", "deal", "free", "mrp", "rate",
            "exp", "company", "supplier"};

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<ProductInput> reader() {
        return new FlatFileItemReaderBuilder<ProductInput>().
                name("productItemReader").resource(
                        new ClassPathResource("medicineInput.csv")).delimited().names(FIELD_NAME).
                fieldSetMapper(new BeanWrapperFieldSetMapper<ProductInput>() {{
            setTargetType(ProductInput.class);
        }}).build();
    }

    @Bean
    public ProductDataProcessor processor() {
        return new ProductDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Product> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Product>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO PRODUCT_TBL (code,name,batch,stock,deal,free,mrp,rate,exp,company,supplier) " +
                        "VALUES (:code,:name,:batch,:stock,:deal,:free,:mrp,:rate,:exp,:company,:supplier)")
                .dataSource(dataSource).build();
    }

    @Bean
    public Job importDataJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importDataJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Product> writer) {
        return stepBuilderFactory.get("step1")
                .<ProductInput, Product> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
