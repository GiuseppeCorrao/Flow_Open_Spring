package develhope.Flow_Open_Spring.auth.entities;

import develhope.Flow_Open_Spring.entities.User;

public class LoginRTO {

    private User user;
    private String JWT;

    public LoginRTO(User user, String JWT) {
        this.user = user;
        this.JWT = JWT;
    }

    public LoginRTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }
}
