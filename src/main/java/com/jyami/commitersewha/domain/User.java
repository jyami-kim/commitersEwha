package com.jyami.commitersewha.domain;

import com.jyami.commitersewha.security.oauth2.user.OAuth2UserInfo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
@Table(indexes = {@Index(columnList = "subId")})
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true)
    private String subId;

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

    private String defaultMajor;

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
    @Builder.Default
    private List<Badge> badgeList = Collections.emptyList();

    @Setter
    @ManyToMany
    @JoinTable(name = "user_dev_stack_link")
    @Builder.Default
    private List<DevStack> devStackList = Collections.emptyList();

    public static User toGoogleInfoEntity(OAuth2UserInfo oAuth2UserInfo) {
        String[] userInfo = devideName(oAuth2UserInfo.getName());
        return User.builder()
                .subId(emailToSubId(oAuth2UserInfo.getEmail()))
                .email(oAuth2UserInfo.getEmail())
                .providerId(oAuth2UserInfo.getId())
                .defaultMajor(userInfo[1])
                .name(userInfo[0])
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .emailVerified(oAuth2UserInfo.getEmailVerified())
                .role(Role.USER)
                .build();
    }

    protected static String emailToSubId(String email) {
        return email.split("@")[0];
    }

    protected static String[] devideName(String googleName) {
        int start = googleName.indexOf('(');
        int end = googleName.indexOf(')');
        return new String[]{googleName.substring(0, start), googleName.substring(start + 1, end)};
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
