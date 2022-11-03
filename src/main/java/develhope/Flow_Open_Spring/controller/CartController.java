package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import develhope.Flow_Open_Spring.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
    ProductRepository productRepository;
    @Autowired
    CartService cartService;
    Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/totalPrice")
    public double totalPrice(){
        return cartService.totalPrice();
    }


    @PostMapping("/buy")
    public List<Product> buy(@RequestBody User user){
        cartService.buy(user);
        return productRepository.findAll();
    }

    @GetMapping("/abort")
    public List<Product> abort(Long id) {
            cartService.abort();
            logger.info("Purchased cancelled");
            return productRepository.findAll();
    }

    @PutMapping("/{id}/addOnCart")
    public ResponseEntity addOnCart(@PathVariable Long id, @RequestBody Product product){
        if(productRepository.existsById(id)) {
            cartService.addOnCart(product);
            logger.info("Product added on the cart");
            return ResponseEntity.status(HttpStatus.OK).body("Product added on cart");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
}
