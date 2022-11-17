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

    private double addComplessivePrice(Long id) {

        Optional<Order> order = orderRepository.findById(id);


        List<Product> listOfProduct = new ArrayList<>();
        for (int i = 0; i < order.get().getProduct().size(); i++) {
            listOfProduct.add(productRepository.findProductById(order.get().getProduct().get(i).getId()));

        }
        int sum = 0;

        for (int i = 0; i < listOfProduct.size(); i++) {
            sum += listOfProduct.get(i).getPrice();
        }
        return sum;


    }


    public void sendToForOrder(Order order) {
        //cambiamento il tuo ordine è stato preso in carico
        User user = userRepository.getReferenceById(order.getUser().getId());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setFrom("f4kemailt3st@gmail.com");
            helper.setSubject("Your order has been processed");
            helper.setText("<h1>Dear " + user.getName() + ",</h1> <h2>we appreciate your choiche and we can tell you tath your order is in working</h2>" + "<h3>Your order: \n " + order.getId() + " \n"  +" the date of your order  is:\n " + order.getDate()  + " the total price is :\n " + order.getComplessiveprice() + "</h3>" + "<img src='cid:thanks' width=600>", true);
            ClassPathResource image = new ClassPathResource("thanks.png");
            helper.addInline("thanks", image);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Order saveOrder(Order order) {


        order.setId(null);

        order.setDate(LocalDate.now());

        Order order1 = orderRepository.save(order);

        order.setComplessiveprice(addComplessivePrice(order1.getId()));

        order.setName("FlowOpenOrder #" + order1.getId() );

        ResponseEntity.status(HttpStatus.OK).build();

        order = orderRepository.save(order);

        sendToForOrder(order);

        return order;

    }
}
