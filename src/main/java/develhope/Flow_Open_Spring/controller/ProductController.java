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
    public ResponseEntity createProduct(@RequestBody Product product) {
        productRepository.save(product);
        logger.info("Product created");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public List<Product> getProduct() {
        logger.info("Products taken");
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneProduct(@PathVariable Long id) throws Exception{
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            productRepository.findById(id);
            logger.info("Single product taken");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else if (findProduct.isEmpty()) {
            logger.error("There is an error: this product doesn't exists");
             ResponseEntity.status(HttpStatus.NOT_FOUND).body("Did not find product");
            throw new Exception("Cannot find this product because is null");
        }
        logger.error("There is an error: BAD_ REQUEST");
         ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Syntax error");
        throw new Exception("BAD_REQUEST Exception.");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) throws Exception{
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            logger.info("Product updated");
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else if (findProduct.isEmpty()) {
            logger.error("There is an error: no products in the db");
             ResponseEntity.status(HttpStatus.NOT_FOUND).body("Did not find product");
            throw new Exception("Cannot update this product because is null");
        }
        logger.error("There is an error: BAD_REQUEST");
         ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Syntax error");
        throw new Exception("BAD_REQUEST Exception.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneProduct(@PathVariable Long id) throws Exception{
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            logger.info("Product deleted");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error("There is an error: can't delete the product because doesn't exists");
             ResponseEntity.status(HttpStatus.NOT_FOUND).body("Did not find the product");
            throw new Exception("Product not found.");
        }
    }

    @DeleteMapping
    public void deleteAll() {
        productRepository.deleteAll();
        logger.info("Products deleted");
    }

}
