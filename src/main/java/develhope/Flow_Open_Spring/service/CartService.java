package develhope.Flow_Open_Spring.service;

import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.OrderRepository;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class CartService {


    /*list for handling the Cart*/
    List<Product> productsOnCart;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    /**
     * @return double sum
     * @Author Giuseppe Corrao
     * @version 1.5
     */
    public double totalPrice() {
        double sum = 0;
        /*cycle on list for operation with price*/
        for (int i = 0; i < productsOnCart.size(); i++) {
            sum += productsOnCart.get(i).getPrice();

        }
        if (sum > 100) {
            return sum;
        } else {
            /*cycle for operation with priceDelivery*/
            for (int i = 0; i < productsOnCart.size(); i++) {
                return sum += productsOnCart.get(i).getPriceDelivery();
            }

        }

        return sum;
    }

    /**
     * @param user
     * @return ResponseEntity
     * @author Giuseppe Corrao
     * @version 3.0
     */
    public ResponseEntity<Order> buy(User user) {
        /*the v variable is the sum of all product's price*/
        var v = totalPrice();
        /*create a new order for this purchase and add variable*/
        Order order = new Order(0L, userRepository.getReferenceById(user.getId()), productsOnCart, LocalDate.now(), v, user.getAddress());
        /*save order in db*/
        orderRepository.save(order);
        /*delete all product on cart*/
        productsOnCart.clear();
        /*send email to user*/
        orderService.sendToForOrder(order);
        /*remove quantity*/
        for (int i = 0; i < productsOnCart.size(); i++) {

            productsOnCart.get(i).setQuantity(productsOnCart.get(i).getQuantity() - 1);
        }
        return ResponseEntity.ok(order);
        //send order to external service
    }

    /**
     * @author Samuele Catalano
     * @author Giuseppe Corrao
     * @version 5.0
     * This method leaves the quantity of the products unchanged when you cancel the purchase
     */
    public void abort() {
        /*empty the cart*/
        productsOnCart.clear();
    }

    /**
     * @param id
     * @return ResponseEntity<Object>String,Product</Object>
     * @Author Giuseppe Corrao
     * @version 2.0
     */
    public ResponseEntity<Object> addOnCart(long id) {
        /*create product from db*/
        Optional<Product> product = productRepository.findProductById(id);
        /*verify if quantity is !0*/
        if (product.get().getQuantity() == 0) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("no item avaible");
        /*verify if product exist*/
        if (productRepository.existsById(id)) {
            productsOnCart.add(productRepository.findProductById(id).get());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

    }

    /**
     * @author Giuseppe Corrao
     * @return List<Product>productOnCart</Product>
     */
    public List<Product> getAllProductsOnCart() {
        return productsOnCart;
    }

}