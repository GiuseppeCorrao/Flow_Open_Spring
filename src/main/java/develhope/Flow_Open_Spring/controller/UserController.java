package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public User getSingleUser(User user, @PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepository.saveAndFlush(user);

    }

    @PutMapping("/update/{id}")
    public HttpStatus updateUser(@RequestBody User user, @PathVariable Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.saveAndFlush(user);
            return HttpStatus.OK;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    @PatchMapping("/patch/{id}")
    public HttpStatus updateOnePartOfUser(@RequestBody User user, @PathVariable Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.saveAndFlush(user);
            return HttpStatus.OK;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(User user, @PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.delete(user);
            return HttpStatus.OK;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteAllUsers(User user, Long id) {
        userRepository.deleteAll();
        return HttpStatus.OK;
    }
}
