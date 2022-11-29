package develhope.Flow_Open_Spring.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    @JsonDeserialize
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String address;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private boolean isActive;
    private String activationCode;
    private String restorePasswordCode;
    private LocalDateTime jwtCreatedOn;

    public User(Long id, String name, String surname, int age, LocalDate birthday, String gender, String address, String email, String password, boolean isActive, String activationCode, String restorePasswordCode, LocalDateTime jwtCreatedOn) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.activationCode = activationCode;
        this.restorePasswordCode = restorePasswordCode;
        this.jwtCreatedOn = jwtCreatedOn;
    }

    public User() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getRestorePasswordCode() {
        return restorePasswordCode;
    }

    public void setRestorePasswordCode(String restorePasswordCode) {
        this.restorePasswordCode = restorePasswordCode;
    }

    public LocalDateTime getJwtCreatedOn() {
        return jwtCreatedOn;
    }

    public void setJwtCreatedOn(LocalDateTime jwtCreatedOn) {
        this.jwtCreatedOn = jwtCreatedOn;
    }
}
