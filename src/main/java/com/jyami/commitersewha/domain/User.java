package com.jyami.commitersewha.domain;

import com.jyami.commitersewha.security.oauth2.user.OAuth2UserInfo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.List;

/**
 * Created by jyami on 2020/09/14
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false)
    @Setter
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Setter
    private String imageUrl;

    @Column(nullable = false)
    @Builder.Default
    private boolean emailVerified = false;

    private String providerId; // Google 에서 부여한 id

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role role;

    // ----------- 여기까지 google 기본 정보 ----------

//    private long githubId; // 이후 깃허브 연동시 사용할 아이디
    @Setter
    private String description;
    @Setter
    private String major;
    @Setter
    private int entranceYear;
    @Setter
    private boolean isGraduate;
    @Setter
    private String job;
    @Setter
    private String company;

    @Setter
    @ManyToMany
    @JoinTable(name = "user_badge_link")
    private List<Badge> badgeList = Collections.emptyList();

    @Setter
    @ManyToMany
    @JoinColumn(name = "user_dev_stack_link")
    private List<DevStack> devStackList = Collections.emptyList();

    public static User toGoogleInfoEntity(OAuth2UserInfo oAuth2UserInfo){
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .providerId(oAuth2UserInfo.getId())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .name(oAuth2UserInfo.getName())
                .emailVerified(oAuth2UserInfo.getEmailVerified())
                .role(Role.USER)
                .build();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Role {

        ADMIN("ROLE_ADMIN", "어드민"),
        USER("ROLE_USER", "사용자");

        private final String key;
        private final String title;

    }



}
