package guru.springframework.spring6restmvc.repositories.auth;


import guru.springframework.spring6restmvc.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}