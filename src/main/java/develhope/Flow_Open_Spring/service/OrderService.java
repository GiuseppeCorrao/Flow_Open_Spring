package develhope.Flow_Open_Spring.service;


import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.OrderRepository;
import develhope.Flow_Open_Spring.repositories.ProductRepository;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * function used on save order for operation
     * @param id
     * @return double
     */

    private double doTotalPrice(Long id) {
        /*create an order from db*/
        Optional<Order> order = orderRepository.findById(id);



        List<Product> listOfProduct = new ArrayList<>();
        /*cycle on listOfProduct for adding product from order*/
        for (int i = 0; i < order.get().getProduct().size(); i++) {
            listOfProduct.add(productRepository.findProductById(order.get().getProduct().get(i).getId()).get());

        }
        /*var for operation*/
        int sum = 0;
        /*cycle on listOfProduct for total price operation*/
        for (int i = 0; i < listOfProduct.size(); i++) {
            sum += listOfProduct.get(i).getPrice();
        }
        return sum;


    }


    /**
     * function for send order by email
     * @param order
     */
    public void sendToForOrder(Order order) {
        /*take user from db by order id*/
        User user = userRepository.getReferenceById(order.getUser().getId());

        /**
         * @see the format must be changed
         */
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setFrom("f4kemailt3st@gmail.com");
            helper.setSubject("Your order has been processed");
            helper.setText("<h1>Dear " + user.getName() + ",</h1> <h2>we appreciate your choiche and we can tell you tath your order is in working</h2>" + "<h3>Your order: \n " + order.getId() + " \n" + " the date of your order  is:\n " + order.getDate() + " the total price is :\n " + order.getComplessiveprice() + "</h3>" + "<img src='cid:thanks' width=600>", true);
            ClassPathResource image = new ClassPathResource("thanks.png");
            helper.addInline("thanks", image);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * function that save the order
     * @param order
     * @return
     */
    public ResponseEntity<Order> saveOrder(Order order) {
        /*set a variable*/
        order.setId(null);
        order.setDate(LocalDate.now());

        /*take order from db*/
        Order order1 = orderRepository.save(order);

        /*set a variable Complessiveprice from function doTotalPrice*/
        order.setComplessiveprice(doTotalPrice(order1.getId()));

        /*set a variable*/
        order.setName("FlowOpenOrder #" + order1.getId());

        order = orderRepository.save(order);

        /*send email to user*/
        sendToForOrder(order);

        return ResponseEntity.ok(order);

    }

    /**
     * @param id
     * @return ResponseEntity<Order>orderRepository.getReferenceById(id)</Order>
     * @author Giuseppe Corrao
     */
    public ResponseEntity<Order> getOrder(Long id) {

        if (!orderRepository.existsById(id)) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(orderRepository.getReferenceById(id));

    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }


    public ResponseEntity<Object> updateOrder(Long id, Order order) {

        order.setId(id);
        Optional<Order> findProduct = orderRepository.findById(id);

        if (findProduct.isPresent()) {

            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.OK).build();

        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Did not find product");

    }

    /**
     * delete a order
     * @param id
     * @return ResponseEntity<String>
     */
    public ResponseEntity<String> delete(Long id) {

        if (orderRepository.existsById(id)) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot delete this order");

        } else {

            return ResponseEntity.status(HttpStatus.OK).body("the order has been deleted");
        }
    }
}