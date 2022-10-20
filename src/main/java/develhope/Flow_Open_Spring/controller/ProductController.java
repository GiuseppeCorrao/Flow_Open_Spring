package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping(value = "/create")
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @GetMapping(value = "")
    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Product> getOneProduct(@PathVariable Long id){
        return productRepository.findById(id);
    }

    @PutMapping(value = "/edit")
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return HttpStatus.ACCEPTED;
        } else {
            return HttpStatus.NO_CONTENT;
        }
    }
}
