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
public class EmailService {


    @Autowired
    JavaMailSender mailSender;


    public void sendTo(String email, String title, String text){
        try{
            MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setFrom("f4kemailt3st@gmail.com");
            helper.setSubject(title);
            helper.setText("<h1>Hello User,</h1> <h2>Welcome to Flow_Open!</h2> <h3>" + text + "</h3>" + "<img src='cid:welcome' width=600>", true);
           ClassPathResource image = new ClassPathResource("welcome.jpg");
          helper.addInline("welcome", image);
            mailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
