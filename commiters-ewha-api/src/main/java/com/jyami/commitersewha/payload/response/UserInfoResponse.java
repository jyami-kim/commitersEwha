package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.tag.Badge;
import com.jyami.commitersewha.domain.tag.DevStack;
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

    private boolean isGraduate;

    private String job;

    private String company;

    @Builder.Default
    private Set<Badge> badges = Collections.emptySet();

    @Builder.Default
    private Set<DevStack> devStacks = Collections.emptySet();

    public static UserInfoResponse fromEntity(User user){
        return UserInfoResponse.builder()
                .subId(user.getSubId())
                .name(user.getName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .description(user.getDescription())
                .major(user.getMajor())
                .entranceYear(user.getEntranceYear())
                .isGraduate(user.isGraduate())
                .job(user.getJob())
                .company(user.getCompany())
                .badges(user.getBadges())
                .devStacks(user.getDevStacks())
                .build();
    }
}