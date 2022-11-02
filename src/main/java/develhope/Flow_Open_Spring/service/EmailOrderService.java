package develhope.Flow_Open_Spring.service;


import develhope.Flow_Open_Spring.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailOrderService {


    @Autowired
    JavaMailSender mailSender;

    public void sendToForOrder(Order order){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(order.getUser().getEmail());
            helper.setFrom("f4kemailt3st@gmail.com");
            helper.setSubject("Your order has been achieved");
            helper.setText("<h1>Dear "+order.getUser()+",</h1> <h2>we appreciate your choiche and we can tell you tath your order is in working</h2>"+"<h3>Your order: \n "+order.getId()+" "+order.getDate()+"the date of ship is:\n "+order.getDate()+"your product is:\n "+order.getProduct()+"the total price is :\n "+order.getPrice()+"</h3>"+ "<img src='cid:shipped' width=600>", true);
            ClassPathResource image = new ClassPathResource("shipped.png");
            helper.addInline("shipped", image);
            mailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
