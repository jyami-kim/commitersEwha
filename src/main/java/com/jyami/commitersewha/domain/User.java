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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    private String description;

    private String major;

    private int entranceYear;

    private boolean isGraduate;

    private String job;

    private String company;

//    private String badgeList;
//
//    private String devStackList;

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

}
