package develhope.Flow_Open_Spring.repositories;

import develhope.Flow_Open_Spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByActivationCode(String activationCode);

    User findByRestorePasswordCode(String restorePasswordCode);
}
