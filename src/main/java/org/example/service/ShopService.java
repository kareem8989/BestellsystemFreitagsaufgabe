package org.example.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.model.Order;
import org.example.model.Product;
import org.example.repo.OrderRepo;
import org.example.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
public class ShopService {
   private ProductRepo productRepo =  new ProductRepo(new ArrayList<>(List.of(
           new Product("1","E-Bike"),
           new Product("2","LaufBand"),
           new Product("3","Smartphone"),
           new Product("4","Laptop"),
           new Product("5","Sportswear"),
           new Product("6","Schutzkleidung")
   )));
   private ProductRepo addedToCar;
    private OrderRepo orderRepo = new OrderRepo(new ArrayList<>()) ;


    public ShopService(OrderRepo orderRepo){
        this.orderRepo = orderRepo;
    }

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Product getProduct(String productId){
        return productRepo.getProductById(productId);
    }

    public String listProducts(){
        return productRepo.listProducts();
    }
    public String listOrderedProducts(){
        return addedToCar.listProducts();
    }

    public void addOrder(Order newOrder){
        orderRepo.addOrder(newOrder);

    }

    public  Order getOrderById(String id){
        return orderRepo.getOrderById(id);

    }

    public String listOrders(){
       return orderRepo.listOrders();
    }

    public void makeOrder(String productIds){

        String[] arrOfIds = productIds.split(" ");
        List<Product> productsList = new ArrayList<>();
        for (Product product: productRepo.getProducts()) {

            for (String arrOfId : arrOfIds) {
                if (product.getId().equals(arrOfId)) {
                    productsList.add(product);
                }

            }
        }
        Order order =  new Order(generateRandomStringId(), productsList);

        orderRepo.addOrder(order);

        addedToCar = new ProductRepo(productsList);

    }

    public String generateRandomStringId(){
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }


    public String getOrderIds(){
        String tbr = "";
        for (Order order :
                orderRepo.getOrders()) {
          tbr +=  order.getId() +"\n";
        }

        return tbr;
    }
}
