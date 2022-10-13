package develhope.Flow_Open_Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDTO {

    String name;
    String surname;
    int age;
    Date birthday;
    String gender;
    String email;
    String password;


}
