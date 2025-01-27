package himanshu.snipsnap.repository;

import himanshu.snipsnap.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users , Long> {
    Optional<Users> findByName(String name);
}
