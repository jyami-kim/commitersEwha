package com.jyami.commitersewha.domain.user;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.tag.Badge;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jyami on 2020/09/14
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
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
    private int entranceYear;
    @Setter
    private boolean isGraduate;
    @Setter
    private String job;
    @Setter
    private String company;
    @Setter
    private String wantJob1;
    @Setter
    private String wantJob2;
    @Setter
    private String wantJob3;
    @Setter
    private String github;
    @Setter
    private String site;

    @Setter
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_badge_link")
    @Builder.Default
    private Set<Badge> badges = new HashSet<>();

    @Setter
    @Builder.Default
    private String devStacks = "";

    public Set<Badge> getBadges() {
        return Collections.unmodifiableSet(badges);
    }

    public void addBadges(List<Badge> baseList){
        badges.addAll(baseList);
    }

    public static String emailToSubId(String email) {
        return email.split("@")[0];
    }

    public static String[] divideName(String googleName) {
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
