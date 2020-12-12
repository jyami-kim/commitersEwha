package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.tag.Badge;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;

import java.util.Collections;
import java.util.Set;

/**
 * Created by jyami on 2020/09/30
 */
@NoArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserInfoResponse {

    private String subId;

    private String name;

    private String email;

    private String imageUrl;

    private boolean emailVerified;

    private User.Role role;

    // ----------- 여기까지 google 기본 정보 ----------

//    private long githubId; // 이후 깃허브 연동시 사용할 아이디

    private String description;

    private String major;

    private int entranceYear;

    private String defaultMajor;

    private boolean isGraduate;

    private String job;

    private String company;

    private String devStacks;

    private String wantJob1;

    private String wantJob2;

    private String wantJob3;

    private String github;

    private String site;

    @Builder.Default
    private Set<Badge> badges = Collections.emptySet();

    public static UserInfoResponse fromEntity(User user){
        return UserInfoResponse.builder()
                .subId(user.getSubId())
                .name(user.getName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .defaultMajor(user.getDefaultMajor())
                .description(user.getDescription())
                .wantJob1(user.getWantJob1())
                .wantJob2(user.getWantJob2())
                .wantJob3(user.getWantJob3())
                .github(user.getGithub())
                .site(user.getSite())
                .entranceYear(user.getEntranceYear())
                .isGraduate(user.isGraduate())
                .job(user.getJob())
                .company(user.getCompany())
                .badges(user.getBadges())
                .devStacks(user.getDevStacks())
                .build();
    }
}
