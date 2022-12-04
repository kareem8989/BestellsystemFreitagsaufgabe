package org.example.repo;

import org.example.model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void listProducts() {
        //Given
                List<Product> productList = new ArrayList<>(List.of(
                                            new Product("1","Kikse"),
                                            new Product("1","Quak"),
                                            new Product("1","Kikse")
        ));
            //When
        ProductRepo productRepo = new ProductRepo(productList);
        String actual = productRepo.listProducts();

        //Then
        assertEquals("""
                Product Name | Product Id
                Kikse | 1
                Quak | 1
                Kikse | 1
                """,actual);
    }

    @Test
    void listProductsWhenIsEmpty() {

        //When
        ProductRepo productRepo = new ProductRepo(new ArrayList<>());
        String actual = productRepo.listProducts();

        //Then
        assertEquals("",actual);
    }
}