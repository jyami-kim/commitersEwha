package com.jyami.commitersewha.domain.githubInfo;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.security.oauth2.user.OAuth2UserInfo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jyami on 2020/10/12
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
public class GithubInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long infoId;

    @Setter
    private String name;

    private String email;

    @Setter
    private String imageUrl;

    private String providerId;

    public static GithubInfo toGithubInfoEntity(OAuth2UserInfo oAuth2UserInfo) {

        return GithubInfo.builder()
                .email(oAuth2UserInfo.getEmail())
                .providerId(oAuth2UserInfo.getId())
                .name(oAuth2UserInfo.getName())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .build();
    }

}
