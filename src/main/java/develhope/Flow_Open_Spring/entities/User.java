package develhope.Flow_Open_Spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;


}
