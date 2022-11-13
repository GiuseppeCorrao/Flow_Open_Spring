package develhope.Flow_Open_Spring.auth.controllers;

import develhope.Flow_Open_Spring.auth.entities.RequestPasswordDTO;
import develhope.Flow_Open_Spring.auth.entities.RestorePasswordDTO;
import develhope.Flow_Open_Spring.auth.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
public class PasswordRestoreController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/request")
    public void request(@RequestBody RequestPasswordDTO requestPasswordDTO) throws Exception {
        passwordService.request(requestPasswordDTO);
    }

    @PostMapping("/response")
    public void response(@RequestBody RestorePasswordDTO restorePasswordDTO) throws Exception{
        passwordService.response(restorePasswordDTO);
    }
}
