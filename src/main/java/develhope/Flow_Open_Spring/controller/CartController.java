package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import develhope.Flow_Open_Spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;



    @PutMapping("/addOnCart/{id}")
    public ResponseEntity<Object> addOnCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.addOnCart(id));
    }

    @GetMapping("/getCartProduct")
    public ResponseEntity<List<Product>> getAllProduct() {
        if (cartService.getAllProductsOnCart().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cartService.getAllProductsOnCart());
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<Double> totalPrice() {
        if (cartService.getAllProductsOnCart().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cartService.totalPrice());
    }



    @GetMapping("/abort")
    public ResponseEntity abort() {
        cartService.abort();
        return ResponseEntity.ok("your cart is now empty");
    }

    /**
     * THIS METHOD MUST CHANGE WITH SESSION LOADER
     * @param 'user' (getContextLoader().getContext().getPrincipal();
     * @return ResponseEntity<Order>order</Order>
     */
    @PostMapping("/buy/{id}")
    public ResponseEntity<Order> buy(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(cartService.buy(id));
    }


}
