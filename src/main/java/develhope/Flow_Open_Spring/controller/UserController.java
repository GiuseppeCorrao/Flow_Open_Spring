package develhope.Flow_Open_Spring.controller;

import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger =  LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> getUsers() {
        logger.info("All users taken");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable Long id) {
        logger.info("Single user taken");
        return userRepository.findById(id).get();
    }

    @PostMapping()
    public ResponseEntity createUser(@RequestBody User user) {
         userRepository.saveAndFlush(user);
         logger.info("User created");
         return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.saveAndFlush(user);
            logger.info("User updated");
            return ResponseEntity.status(HttpStatus.OK).body("Successful of the request! The user has been updated");
        } else if(!userRepository.existsById(id)){
            logger.error("Can't update this user: user doesn't exists");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the user");
        }else{
            logger.error("Can't update this user: conflict in your request");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is a conflict in your request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            logger.info("User deleted");
            return (ResponseEntity) ResponseEntity.status(HttpStatus.OK).body("Successful of the request! The user has been deleted");
        } else if(!userRepository.existsById(id)){
            logger.error("Can't delete this user: user doesn't exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find the user");
        }else{
            Error error = new Error("conflict in your request");
            logger.error("Can't delete this user: conflict in your request ");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is a conflict in your request");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAllUsers() {
        userRepository.deleteAll();
        logger.info("All users deleted");
        return (ResponseEntity) ResponseEntity.status(HttpStatus.OK);
    }
}
