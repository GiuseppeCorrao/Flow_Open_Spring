package develhope.Flow_Open_Spring.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import develhope.Flow_Open_Spring.auth.entities.LoginDTO;
import develhope.Flow_Open_Spring.auth.entities.LoginRTO;
import develhope.Flow_Open_Spring.config.WebSecurity;
import develhope.Flow_Open_Spring.entities.User;
import develhope.Flow_Open_Spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public LoginRTO login(LoginDTO loginDTO) throws Exception{
        if(loginDTO == null) return null;
        User userInDB = userRepository.findByEmail(loginDTO.getEmail());
        if(userInDB == null || !userInDB.isActive()) return null;
        boolean canLogin = this.canUserLogin(userInDB, loginDTO.getPassword());
        if(!canLogin) return null;
        String JWT = generateJWT(userInDB);
        userInDB.setPassword(null);
        LoginRTO out = new LoginRTO();
        out.setJWT(JWT);
        out.setUser(userInDB);
        return out;
    }

    public boolean canUserLogin(User user, String password){
        return passwordEncoder.matches(password,user.getPassword());
    }

    public String generateJWT(User user){
        String JWT = getJWT(user);
        user.setJwtCreatedOn(LocalDateTime.now());
        userRepository.save(user);
        return JWT;
    }

    public static Date convertToDateViaInstant(LocalDateTime dateTime){
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getJWT(User user) {
        Date expire = convertToDateViaInstant(LocalDateTime.now().plusDays(15));
        return JWT.create().withIssuer("flowopen").withIssuedAt(new Date()).withExpiresAt(expire)
                .withClaim("id", user.getId()).sign(Algorithm.HMAC512(WebSecurity.secret));
    }


}
