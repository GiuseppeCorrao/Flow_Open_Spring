package develhope.Flow_Open_Spring.service;

import develhope.Flow_Open_Spring.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    List<Product> productsOnCart;

    public CartService(List<Product> productsOnCart) {
        this.productsOnCart = productsOnCart;
    }



    public void buy() {
        productsOnCart.clear();
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