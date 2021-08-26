package com.devjava.hourday.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Builder.Default
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = Boolean.FALSE;

    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    public void encodePassword(String password) {
        this.password = password;
    }

}
