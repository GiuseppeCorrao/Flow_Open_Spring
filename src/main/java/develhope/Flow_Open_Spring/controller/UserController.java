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

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userRepository.saveAndFlush(user);

    }

    @PutMapping("/{id}")
    public HttpStatus updateUser(@RequestBody User user, @PathVariable Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.saveAndFlush(user);
            return HttpStatus.OK;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.CONFLICT;
        }
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
