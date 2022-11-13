package develhope.Flow_Open_Spring.auth.controllers;

import develhope.Flow_Open_Spring.auth.entities.SignupActivationDTO;
import develhope.Flow_Open_Spring.auth.entities.SignupDTO;
import develhope.Flow_Open_Spring.auth.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupDTO signupDTO) throws Exception{
        signupService.signup(signupDTO);
    }

    @PostMapping("/signup/activation")
    public void activate(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception{
        signupService.activate(signupActivationDTO);
    }
}
