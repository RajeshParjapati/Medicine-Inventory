package com.inventory.repository;

import com.inventory.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 2)	Given a supplier id or name, list all the products that the supplier has with stock.
 * 3)	Accept a search param for the above filter based on the product name.
 * 4)	Accept a param for the above api,
 * or create a new endpoint to only list out products that didn’t yet expire for that supplier or list of suppliers.
 * 5)	Make the above api’s pageable.
 */

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product>  findBySupplierContaining(String supplierName, PageRequest request);

    //@Query("select p from product_tbl p where p.supplier like %?1")
    List<Product> findBySupplierContainingAndNameContains(String supplierName,String name, PageRequest request);

    List<Product> findAllByName(String name, PageRequest request);

    List<Product> findByExpGreaterThanEqual(LocalDate date, PageRequest request);

}
