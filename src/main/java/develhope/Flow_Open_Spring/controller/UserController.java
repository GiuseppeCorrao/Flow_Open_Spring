package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping()
    public ResponseEntity createUser(@RequestBody User user) {
         userRepository.saveAndFlush(user);
         return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.saveAndFlush(user);
            return ResponseEntity.status(HttpStatus.OK).body("Successful of the request! The user has been updated");
        } else if(!userRepository.existsById(id)){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the user");
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is a conflict in your request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return (ResponseEntity) ResponseEntity.status(HttpStatus.OK).body("Successful of the request! The user has been deleted");
        } else if(!userRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the user");
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is a conflict in your request");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAllUsers() {
        userRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
