package me.maxallgaier.synapsis.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", length = 16, nullable = false)
    private String username;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "password_hash", length = 60, nullable = false)
    private String passwordHash;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10, nullable = false)
    private Role role;
}
