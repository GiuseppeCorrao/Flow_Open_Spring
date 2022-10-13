package develhope.Flow_Open_Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDTO {


    private long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private  int age;
    @NonNull
    private Date birthday;
    @NonNull
    private String gender;
    @NonNull
    private String email;
    @NonNull
    private String password;


}
