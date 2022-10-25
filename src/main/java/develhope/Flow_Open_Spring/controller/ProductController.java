package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProduct(@PathVariable Long id) {
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            productRepository.findById(id);
            ResponseEntity.status(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not find product!");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            productRepository.save(product);
            ResponseEntity.status(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not find product!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not find the product");
        }
    }

    @DeleteMapping
    public void deleteAll() {
        productRepository.deleteAll();
    }

}
