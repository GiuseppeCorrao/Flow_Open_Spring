package develhope.Flow_Open_Spring.auth.controllers;

import develhope.Flow_Open_Spring.auth.entities.*;
import develhope.Flow_Open_Spring.auth.services.LoginService;
import develhope.Flow_Open_Spring.auth.services.PasswordService;
import develhope.Flow_Open_Spring.auth.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupDTO signupDTO) throws Exception {
        signupService.signup(signupDTO);
    }

    @PostMapping("/signup/activation")
    public void activate(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception {
        signupService.activate(signupActivationDTO);
    }

    @PostMapping("/login")
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception {
        LoginRTO loginRTO = loginService.login(loginDTO);
        if (loginDTO == null) throw new Exception("Cannot login");
        return loginRTO;
    }

    @PostMapping("/password/request")
    public void request(@RequestBody RequestPasswordDTO requestPasswordDTO) throws Exception {
        passwordService.request(requestPasswordDTO);
    }

    @PostMapping("/password/restore")
    public void restore(@RequestBody RestorePasswordDTO restorePasswordDTO) throws Exception {
        passwordService.restore(restorePasswordDTO);
    }


}
