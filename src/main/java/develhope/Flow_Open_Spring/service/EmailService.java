package develhope.Flow_Open_Spring.service;



import develhope.Flow_Open_Spring.entities.Order;

import develhope.Flow_Open_Spring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {


    @Autowired
    JavaMailSender mailSender;

    public void mailForActivationCode(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setFrom("f4kemailt3st@gmail.com");
            helper.setSubject("Activation code");
            helper.setText("<h1>Hello User,</h1> <h2>click to the seguent link for active your profile:</h2> <h3>" + "http://localhost:4040/auth/signup/activation" + "</h3>" + "<img src='cid:activationcode' width=600>", true);
            helper.setText("Your activation code is: " + user.getActivationCode());
            helper.addInline("activationcode", new ClassPathResource("activationcode.jpg"));
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mailForRestorePassword(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setFrom("f4kemailt3st@gmail.com");
            helper.setSubject("Password restore");
            helper.setText("<h1>Hello User!</h1> <h2>Password lost? click to the seguent link for the restore of your password: </h2> <h3>" + "http://localhost:4040/auth/password/restore" + "</h3>" + "<img src='cid:restorepassword' width=600>", true);
            helper.setText("The code for the restore of the password is: " + user.getRestorePasswordCode());
            helper.addInline("restorepassword", new ClassPathResource("restorepassword.jpg"));
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
