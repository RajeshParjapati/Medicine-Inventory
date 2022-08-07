package com.inventory.batch;

import com.inventory.dto.ProductInput;
import com.inventory.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProductDataProcessor implements ItemProcessor<ProductInput, Product> {

    private static final Logger log = LoggerFactory.getLogger(ProductDataProcessor.class);

    @Override
    public Product process(final ProductInput productInput) throws Exception {
        DateTimeFormatter dateformator = DateTimeFormatter.ofPattern("d-MM-yyyy");
        Product product = new Product();
        //product.setId(0);
        product.setCode(productInput.getCode());
        product.setName(productInput.getName());
        product.setBatch(productInput.getBatch());
        product.setStock(productInput.getStock());
        product.setDeal(productInput.getDeal());
        product.setFree(productInput.getFree());
        product.setMrp(productInput.getMrp());
        product.setRate(productInput.getRate());
        product.setExp(LocalDate.parse(productInput.getExp(), dateformator));
        product.setCompany(productInput.getCompany());
        product.setSupplier(productInput.getSupplier());

        return product;
    }

}