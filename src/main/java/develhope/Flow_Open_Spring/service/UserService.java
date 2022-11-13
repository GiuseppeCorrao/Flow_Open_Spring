package develhope.Flow_Open_Spring.service;

import develhope.Flow_Open_Spring.entities.User;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    List<User> users;

    public User getUserById(Long userId) throws Exception {
        Optional<User> studentOptional = users.stream().filter(user -> user.getId().equals(userId)).findAny();
        if (studentOptional.isPresent()){
            return studentOptional.get();
        }else throw new Exception("Cannot find this id");
    }

}
