package org.example;
import org.example.service.ShopService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ShopService myShopService = new ShopService();
        listProducts();

        System.out.println("---------------------------------");

        System.out.println("please give the product id to select:\nto choose more then one product enter new Line to select the next:\nto collect the products enter (collect):");

        Scanner scr = new Scanner(System.in);

        String id;
        StringBuilder collectsIds = new StringBuilder();
        while (!(id = scr.nextLine()).equals("collect")){
            collectsIds.append(id).append(" ");
        }
        myShopService.makeOrder(collectsIds.toString());


        System.out.println("Your Random Order id(s): "+ myShopService.getOrderIds());

        System.out.println("Your ordered/selected Products: \n"+ myShopService.getAddedToCar().listProducts());
        System.out.println("===========================");
        System.out.println("your order will arrive to you in the next workDays. \nthank you :)");



    }

    public static void listProducts(){
        System.out.println(new ShopService().listProducts());
    }
}