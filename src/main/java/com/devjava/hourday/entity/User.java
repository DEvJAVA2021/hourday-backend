package com.devjava.hourday.entity;

import com.devjava.hourday.common.jwt.auth.Authority;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "login_fail_count")
    private int loginFailCount;

    @Column(name = "is_lock")
    private Boolean isLock = Boolean.FALSE;

    @Column(name = "lock_count")
    private int lockCount;

    @Column(name = "latest_lock_date")
    private LocalDateTime latestLockDate;

    @Builder.Default
    private final String authority = Authority.ROLE_USER;

    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList = new ArrayList<>();

    public void encodePassword(String password) {
        this.password = password;
    }

}
