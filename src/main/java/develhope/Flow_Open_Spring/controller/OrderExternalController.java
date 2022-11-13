package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Order")
public class OrderExternalController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("")
    public ResponseEntity postOrder(@RequestParam Order order) throws Exception {

        if (orderRepository.existsById(order.getId())) {

            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the id already exist on database");
            throw new Exception("Id already exists");
        } else {
            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.OK).body("the order as been recived");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) throws Exception {

        orderRepository.deleteById(id);

        if (orderRepository.existsById(id)) {

             ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot delete this order");
            throw new Exception("This order doesn't exists ");
        } else {

            return ResponseEntity.status(HttpStatus.OK).body("the order has been deleted");
        }
    }

    @GetMapping("")
    public Page<Order> getMultiple(@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size) throws Exception{

        if (page.isPresent() && size.isPresent()) {

            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "Date"));

            Pageable pageable = PageRequest.of(page.get(), size.get(), sort);

            Page<Order> orders = orderRepository.findAll(pageable);

            return orders;

        } else {
            throw new Exception("Page not found or size wrong");

        }

    }
}
