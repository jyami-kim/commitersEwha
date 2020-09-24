package com.jyami.commitersewha.domain;

import com.jyami.commitersewha.security.oauth2.user.OAuth2UserInfo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    private boolean emailVerified = false;

    private String providerId;

    public static User toEntity(OAuth2UserInfo oAuth2UserInfo){
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .providerId(oAuth2UserInfo.getId())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .name(oAuth2UserInfo.getName())
                .emailVerified(oAuth2UserInfo.getEmailVerified())
                .build();
    }

}
