package me.maxallgaier.synapsis.user;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@NullMarked
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateInfo info) {
        var emailAlreadyRegistered = this.userRepository.existsUserByEmail(info.email());
        if (emailAlreadyRegistered) {
            throw new UserAlreadyExistsException();
        }

        var user = User.builder()
            .email(info.email())
            .username(info.username())
            .passwordHash(info.passwordHash())
            .firstName(info.firstName())
            .lastName(info.lastName())
            .role(Role.MEMBER)
            .build();

        return this.userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
