package org.example.repo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Order;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRepo {

    private List<Order> orders;

    public String listOrders(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < orders.size(); i++) {
            str.append(orders.get(i).toString());
            if(i != orders.size() - 1)
                str.append("\n");
        }
        return str.toString();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }

    }
        throw new NoSuchElementException("the Order is not found");
  }

  public void addOrder(Order newOrder){
        orders.add(newOrder);
  }
}