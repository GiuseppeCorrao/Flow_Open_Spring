package develhope.Flow_Open_Spring.service;

import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.OrderRepository;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class CartService {



    List<Product> productsOnCart;

    //add variable for pricedelivery
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailOrderService emailOrderService;

    public CartService(List<Product> productsOnCart) {
        this.productsOnCart = productsOnCart;
    }


    public void buy(User user) {
        var v = totalPrice();
        Order order = new Order(1, userRepository.getReferenceById(user.getId()), productsOnCart, LocalDate.now(), v);
        orderRepository.save(order);
        productsOnCart.clear();
        emailOrderService.sendToForOrder(order);
        //send order to external service
    }


    /**
     * @author Samuele Catalano
     * @version 4.0
     * This method leaves the quantity of the products unchanged when you cancel the purchase
     */
    public void abort() {

        productsOnCart.clear();
    }


    public void addOnCart(Product p) {
        productsOnCart.add(p);
    }


    public double totalPrice() {
        double sum = 0;
        for (int i = 0; i < productsOnCart.size(); i++) {
            sum += productsOnCart.get(i).getPrice();

        }
        if (sum > 30) {
            return sum;
        } else {
            for (int i = 0; i < productsOnCart.size(); i++) {
                return sum += productsOnCart.get(i).getPriceDelivery();
            }

        }

        return sum;
    }

    public List<Product> getProductsOnCart() {
        return productsOnCart;
    }

    public void setProductsOnCart(List<Product> productsOnCart) {
        this.productsOnCart = productsOnCart;
    }
}