package kelompok4.backend.repository;

import kelompok4.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // pastikan ini adalah method yang benar
}
