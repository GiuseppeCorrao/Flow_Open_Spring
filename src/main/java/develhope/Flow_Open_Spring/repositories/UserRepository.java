package develhope.Flow_Open_Spring.repositories;

import develhope.Flow_Open_Spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
