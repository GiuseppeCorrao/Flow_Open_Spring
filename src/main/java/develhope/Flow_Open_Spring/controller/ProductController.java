package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product product1 = productRepository.save(product);
        logger.info("Product created");
        return ResponseEntity.ok(product1);
    }

    @GetMapping
    public List<Product> getProduct() {
        logger.info("Products taken");
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends Object> getOneProduct(@PathVariable Long id) {
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            Optional<Product> product = productRepository.findById(id);
            logger.info("Single product taken");
            return ResponseEntity.ok(product.get());
        } else if (findProduct.isEmpty()) {
            logger.error("There is an error: no products in the db");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Did not find product");
        }
        logger.error("There is an error: BAD_ REQUEST");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Syntax error");
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            logger.info("Product updated");
            productRepository.save(product);
            return ResponseEntity.ok(product);
        } else if (findProduct.isEmpty()) {
            logger.error("There is an error: no products in the db");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Did not find product");
        }
        Error error = new Error("BAD_REQUEST");
        logger.error("There is an error: " + error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Syntax error");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            logger.info("Product deleted");
            return ResponseEntity.ok("the product has been deleted");
        } else {
            logger.error("There is an error: can't delete the product because doesn't exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Did not find the product");
        }
    }

    @DeleteMapping
    public void deleteAll() {
        productRepository.deleteAll();
        logger.info("Products deleted");
    }

}
