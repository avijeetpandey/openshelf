package com.avijeet.openshelf.entities;

import com.avijeet.openshelf.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name is required")
    private String firstName;


    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = true)
    private List<Book> books;
}
