package me.maxallgaier.synapsis.user;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@NullMarked
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsUserByEmail(String email);
}
