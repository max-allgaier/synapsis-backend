package me.maxallgaier.synapsis.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
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
        var savedUser = this.userRepository.save(user);
        log.info("created user id={} email={} username={}", savedUser.id(), savedUser.email(), savedUser.username());

        return savedUser;
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
