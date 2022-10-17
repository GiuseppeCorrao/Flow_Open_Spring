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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private  int age;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false)
    private String gender;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;


}
