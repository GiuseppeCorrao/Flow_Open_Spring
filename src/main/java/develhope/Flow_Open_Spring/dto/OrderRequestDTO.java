package develhope.Flow_Open_Spring.dto;

import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.entities.User;

import java.util.List;

public class OrderRequestDTO {

    User user;

    List<Product> products;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
