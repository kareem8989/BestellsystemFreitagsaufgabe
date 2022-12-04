package org.example.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Product;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRepo {

   private List<Product> products ;

   public String listProducts(){
       StringBuilder str = new StringBuilder();
       for (int i = 0; i < products.size(); i++) {
           str.append(products.get(i));

           if(i != products.size() - 1)
               str.append("\n");
       }
       return str.toString();
   }

   public Product getProductById(String id){
       for (Product product : products) {
           if (product.getId().equals(id)){
               return product;
           }
       }
       throw new NoSuchElementException("Product not found:  ");
   }


}
