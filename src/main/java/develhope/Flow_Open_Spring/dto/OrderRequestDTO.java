package develhope.Flow_Open_Spring.dto;

import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.entities.User;

import java.util.List;

public class OrderRequestDTO {

    private Order order;

    public OrderRequestDTO(){

    }
    public OrderRequestDTO(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "order=" + order +
                '}';
    }
}
