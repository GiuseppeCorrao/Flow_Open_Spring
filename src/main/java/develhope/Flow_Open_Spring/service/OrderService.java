package develhope.Flow_Open_Spring.service;


import develhope.Flow_Open_Spring.entities.Order;
import develhope.Flow_Open_Spring.entities.Product;
import develhope.Flow_Open_Spring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OrderRepository orderRepository;

    public void sendToForOrder(Order order) {
        //cambiamento il tuo ordine è stato preso in carico

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(order.getUser().getEmail());
            helper.setFrom("f4kemailt3st@gmail.com");
            helper.setSubject("Your order has been achieved");
            helper.setText("<h1>Dear " + order.getUser() + ",</h1> <h2>we appreciate your choiche and we can tell you tath your order is in working</h2>" + "<h3>Your order: \n " + order.getId() + " " + order.getDate() + "the date of ship is:\n " + order.getDate() + "your product is:\n " + order.getProduct() + "the total price is :\n " + order.getPrice() + "</h3>" + "<img src='cid:shipped' width=600>", true);
            ClassPathResource image = new ClassPathResource("shipped.png");
            helper.addInline("shipped", image);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity saveOrder(Order order) {

        order.setId(null);

        order.setDate(LocalDate.now());

        Order order1 = order;

        List<Product> listOfproduct = order1.getProduct();

        double sum = 0;

        for (int i = 0; i < listOfproduct.size(); i++) {
            sum += listOfproduct.get(i).getPrice();
        }

        order.setPrice(sum);

        if (orderRepository.existsById(order.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("can't save the order");
        } else {
            orderRepository.save(order);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
