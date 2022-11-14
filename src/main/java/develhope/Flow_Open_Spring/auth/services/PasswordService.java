package develhope.Flow_Open_Spring.auth.services;

import develhope.Flow_Open_Spring.auth.entities.RequestPasswordDTO;
import develhope.Flow_Open_Spring.auth.entities.RestorePasswordDTO;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import develhope.Flow_Open_Spring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void request(RequestPasswordDTO requestPasswordDTO) throws Exception {
        User user = userRepository.findByEmail(requestPasswordDTO.getEmail());
        if (user == null) throw new Exception("Cannot find this user");
        user.setRestorePasswordCode(UUID.randomUUID().toString());
        mailSender.mailForRestorePassword(user);
        userRepository.save(user);
    }

    public void restore(RestorePasswordDTO restorePasswordDTO) throws Exception {
        User user = userRepository.findByRestorePasswordCode(restorePasswordDTO.getRestorePasswordCode());
        if (user == null) throw new Exception("Cannot find this user");
        user.setPassword(passwordEncoder.encode(restorePasswordDTO.getNewPassword()));
        user.setRestorePasswordCode(restorePasswordDTO.getRestorePasswordCode());
        user.setActive(true);
        user.setActivationCode(null);
        userRepository.save(user);
    }
}
