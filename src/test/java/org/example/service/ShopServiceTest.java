package org.example.service;

import org.example.model.Order;
import org.example.model.Product;
import org.example.repo.OrderRepo;
import org.example.repo.ProductRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void getProduct() {
        //Given
        // When
        Product actual = new ShopService().getProduct("2");

        // then
        assertEquals( new Product("2","LaufBand"),actual);
    }

    @Test
    void thrownExceptionWhenProductNotExsist() {
        //Given
        ShopService shs = new ShopService();

        // get product from the ShopService and give it to the order1
        Order order1 = new Order("123",new ArrayList<>(List.of(shs.getProduct("1") )));

        // initial  OrderRepo to have order in ShopService
        OrderRepo orderRepo = new OrderRepo(new ArrayList<>(List.of(order1)));
         final ShopService actual = new ShopService(orderRepo);

        // When
        // then
        assertThrows(NoSuchElementException.class,()-> actual.getOrderById("2323"));

    }

        @Test
        void listProducts() {
            //Given

            //When
            String  actual = new ShopService().listProducts();

            //Then
            assertEquals("""
                    Product(id=1, name=E-Bike)
                    Product(id=2, name=LaufBand)
                    Product(id=3, name=Smartphone)
                    Product(id=4, name=Laptop)
                    Product(id=5, name=Sportswear)
                    Product(id=6, name=Schutzkleidung)""",actual);
        }

        @Test
        void listProductsWhenIsEmpty() {
            //When
            ProductRepo productRepo = new ProductRepo(new ArrayList<>());
            String  actual = new ShopService(productRepo, new OrderRepo(new ArrayList<>())).listProducts();

            //Then
            assertEquals("",actual);
        }


    @Test
    void addOrder() {

        //Given
        ShopService shs = new ShopService();

        // get product from the ShopService and give it to the order1
        Order order1 = new Order("123",new ArrayList<>(List.of(shs.getProduct("1") )));

        // initial  Order repo to have order in ShopService
        OrderRepo orderRepo = new OrderRepo(new ArrayList<>(List.of(order1)));

        shs = new ShopService(orderRepo);

        // get new product from ShopService and make new Order and added to the
        Order order2 = new Order("456",new ArrayList<>(List.of( shs.getProduct("2"))));
        shs.addOrder(order2);

        // When
       String actual =  shs.listOrders();

        // Then
        assertEquals(
                "Order(id=123, products=[Product(id=1, name=E-Bike)])\n" +
                        "Order(id=456, products=[Product(id=2, name=LaufBand)])",actual);
    }

    @Test
    void getOrder() {
        //Given


        ShopService shs = new ShopService();

        Order order1 = new Order("123",new ArrayList<>(List.of(shs.getProduct("1"))));
        Order order2 = new Order("122",new ArrayList<>(List.of(shs.getProduct("2"))));

        OrderRepo orderRepo = new OrderRepo(new ArrayList<>(List.of(order1,order2)));
        shs = new ShopService(orderRepo);

        // When
        Order  actual = shs.getOrderById("123");


        // Then

        assertEquals(
                new Order("123",new ArrayList<>(List.of(shs.getProduct("1")))),actual);

    }


    @Test
    void getOrderShouldThrownException() {
        //Given
        ShopService shs = new ShopService();
        List<Product> productList1 = new ArrayList<>(List.of(shs.getProduct("1"), shs.getProduct("2")));
        List<Product> productList2 =  new ArrayList<>(List.of(shs.getProduct("3"), shs.getProduct("4")));

        List<Order> orders = new ArrayList<>(List.of(new Order("123",productList1),new Order("452",productList2)));

        OrderRepo orderRepo = new OrderRepo(orders);
        final ShopService actual = new ShopService(orderRepo);

        // When
        //Then
        assertThrows(NoSuchElementException.class,()-> actual.getOrderById("232"));


        }


    @Test
    void listOrders() {

        //Given
        ShopService shs = new ShopService();                        // get product from the existing product and pass it to eht ArrayList in the Orders
        Order order1 = new Order("123",new ArrayList<>(List.of( shs.getProduct("1"), shs.getProduct("2"))));
        Order order2 = new Order("456",new ArrayList<>(List.of( shs.getProduct("3"), shs.getProduct("4"), shs.getProduct("5"))));
        List<Order> orders = new ArrayList<>(List.of(order1,order2));

        OrderRepo orderRepo = new OrderRepo(orders);

        shs = new ShopService(orderRepo);
        // When
        String actual = shs.listOrders();


        //Then
        assertEquals("Order(id=123, products=[Product(id=1, name=E-Bike), Product(id=2, name=LaufBand)])\n" +
                             "Order(id=456, products=[Product(id=3, name=Smartphone), Product(id=4, name=Laptop), Product(id=5, name=Sportswear)])",actual);

    }

    @Test
    void makeOrder() {
    }
}