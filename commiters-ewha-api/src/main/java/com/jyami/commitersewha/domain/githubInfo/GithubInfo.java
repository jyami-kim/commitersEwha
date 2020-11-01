package com.jyami.commitersewha.domain.githubInfo;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.security.oauth2.user.OAuth2UserInfo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @Setter
    private String token;

    @OneToOne
    @JoinColumn(name = "userId")
    @NotNull
    private User user;

    public static GithubInfo toGithubInfoEntity(OAuth2UserInfo oAuth2UserInfo, User user) {

        return GithubInfo.builder()
                .email(oAuth2UserInfo.getEmail())
                .providerId(oAuth2UserInfo.getId())
                .name(oAuth2UserInfo.getName())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .user(user)
                .build();
    }

    public String getToken() {
        return "token " + token;
    }

}
