package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.dto.OrderRequestDTO;
import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.repositories.OrderRepository;
import develhope.Flow_Open_Spring.service.OrderService;
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
@RequestMapping("/order")
/*add PreRequest for Admin (limit this access)*/
public class OrderExternalController {


    @Autowired
    private OrderService orderService;



    @PostMapping("")
    public @ResponseBody ResponseEntity<Order> postOrder(@RequestBody Order order) throws Exception {

        ResponseEntity.status(HttpStatus.OK).body("the order has been recived");

        return orderService.saveOrder(order);
    }


    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("")
    public Page<Order> getMultiple(@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size) {

        if (page.isPresent() && size.isPresent()) {

            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "Date"));

            Pageable pageable = PageRequest.of(page.get(), size.get(), sort);

            Page<Order> orders = orderService.findAll(pageable);

            return orders;

        } else {
            Page<Order> pageOrder = null;

            return pageOrder;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable Long id, @RequestBody Order order) {

       return orderService.updateOrder(id, order);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {

        return ResponseEntity.ok(orderService.delete(id));

    }
}
