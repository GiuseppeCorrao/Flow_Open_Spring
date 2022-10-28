package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import develhope.Flow_Open_Spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
    ProductRepository productRepository;
    @Autowired
    CartService cartService;

    @GetMapping("/totalPrice")
    public double totalPrice(){
        return cartService.totalPrice();
    }

    @DeleteMapping("/buy")
    public List<Product> buy(){
        cartService.buy();
        return productRepository.findAll();
    }

    @GetMapping("/abort")
    public List<Product> abort(Long id) {
            cartService.abort();
            return productRepository.findAll();
    }

    @PutMapping("/{id}/addOnCart")
    public ResponseEntity addOnCart(@PathVariable Long id, @RequestBody Product product){
        if(productRepository.existsById(id)) {
            cartService.addOnCart(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product added on cart");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
}
