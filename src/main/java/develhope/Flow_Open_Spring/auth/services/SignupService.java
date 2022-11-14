package develhope.Flow_Open_Spring.auth.services;

import develhope.Flow_Open_Spring.auth.entities.SignupActivationDTO;
import develhope.Flow_Open_Spring.auth.entities.SignupDTO;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import develhope.Flow_Open_Spring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public void signup(SignupDTO signupDTO) throws Exception {
        User userFromDB = userRepository.findByEmail(signupDTO.getEmail());
        if(userFromDB != null) throw new Exception("Email already taken");
        User user = new User();
        user.setName(signupDTO.getName());
        user.setSurname(signupDTO.getSurname());
        user.setAge(signupDTO.getAge());
        user.setBirthday(signupDTO.getBirthday());
        user.setGender(signupDTO.getGender());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        emailService.mailForActivationCode(user);
        userRepository.save(user);
    }

    public void activate(SignupActivationDTO signupActivationDTO) throws Exception{
        User user = userRepository.findByActivationCode(signupActivationDTO.getActivationCode());
        if(user == null) throw new Exception("Cannot find the user");
        user.setActive(true);
        user.setActivationCode(signupActivationDTO.getActivationCode());
        userRepository.save(user);
    }
}
