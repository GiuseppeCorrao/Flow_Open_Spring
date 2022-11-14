package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.dto.OrderRequestDTO;
import develhope.Flow_Open_Spring.entities.Order;
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
public class OrderExternalController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;


    @PostMapping("")
    public ResponseEntity postOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws Exception {

        Order order1 = new Order();
        order1.setUser(orderRequestDTO.getUser());
        order1.setProduct(orderRequestDTO.getProducts().stream().toList());

        orderService.saveOrder(order1);

        orderService.sendToForOrder(orderRepository.getReferenceById(order1.getId()));


        return ResponseEntity.status(HttpStatus.OK).body("the order as been recived");
    }


    @DeleteMapping("{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {

        orderRepository.deleteById(id);

        if (orderRepository.existsById(id)) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot delete this order");

        } else {

            return ResponseEntity.status(HttpStatus.OK).body("the order has been deleted");
        }
    }

    @GetMapping("{id}")
    public @ResponseBody ResponseEntity getOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isPresent()) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("can't find the order ");
    }

    @GetMapping("")
    public Page<Order> getMultiple(@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size) {

        if (page.isPresent() && size.isPresent()) {

            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "Date"));

            Pageable pageable = PageRequest.of(page.get(), size.get(), sort);

            Page<Order> orders = orderRepository.findAll(pageable);

            return orders;

        } else {
            Page<Order> pageOrder = null;

            return pageOrder;
        }

    }
}
