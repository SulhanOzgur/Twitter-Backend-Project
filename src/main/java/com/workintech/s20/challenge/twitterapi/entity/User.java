package com.workintech.s20.challenge.twitterapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user", schema = "s20challenge")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotEmpty
    @NotNull
    @NotBlank
    private Long id;

    @Column(name = "first_name")
    @Size(max = 70)
    @NotEmpty
    @NotNull
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 70)
    @NotEmpty
    @NotNull
    @NotBlank
    private String lastName;

    @Column(name = "user_name")
    @Size(max = 70)
    @NotEmpty
    @NotNull
    @NotBlank
    private String userName;

    @Column(name = "password")
    @Size(max = 70)
    @NotEmpty
    @NotNull
    @NotBlank
    private String password;

    @Column(name = "email")
    @Size(max = 70)
    @NotEmpty
    @NotNull
    @NotBlank
    private String email;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    @NotEmpty
    @NotNull
    @NotBlank
    private Role role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Tweet> tweets;

}
