package com.inventory.service;

import com.inventory.batch.JobCompletionNotificationListener;
import com.inventory.businessException.ProductNotFoundException;
import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository repository;

    public List<Product> findBySupplierContaining(String supplierName, int offset, int pageSize){

        List<Product> productsList = repository.findBySupplierContaining(
                supplierName,PageRequest.of(offset,pageSize));
        if(!productsList.isEmpty()){
            return productsList;
        }else{
            throw new ProductNotFoundException("Product not found for the Supplier Name : "+supplierName);
        }
    }

    public List<Product> findAllByName(String supplierName, String productName, int offset, int pageSize){
        List<Product> productsList = repository.findBySupplierContainingAndNameContains(
                supplierName, productName, PageRequest.of(offset,pageSize));
        if(!productsList.isEmpty()){
            return productsList;
        }else{
            throw new ProductNotFoundException("Product not found for the Supplier Name :" +supplierName +
                    "or search with product name: "+productName);
        }
    }

    public List<Product> findAllActive(int offset, int pageSize){
        return repository.findByExpGreaterThanEqual(LocalDate.now(), PageRequest.of(offset,pageSize));
    }

}
