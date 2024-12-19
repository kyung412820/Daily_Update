package com.example.demo.domain;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 유저명

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
