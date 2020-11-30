package com.jyami.commitersewha.domain.userRank;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by jyami on 2020/11/28
 */

@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
public class UserRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rankId")
    private long rankId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infoId")
    private GithubInfo githubInfo;

    private long score;

    private long commitCount;

    private long commitMaxCombo;

    private boolean week;

    private LocalDate localDate;

    public static UserRank emptyUserRank(GithubInfo githubInfo) {
        return UserRank.builder()
                .githubInfo(githubInfo)
                .score(0)
                .commitCount(0)
                .commitMaxCombo(0)
                .build();
    }

    public void updateUserRank(UserRank userRank) {
        this.score = userRank.score;
        this.commitCount = userRank.commitCount;
        this.commitMaxCombo = userRank.commitMaxCombo;
        this.week = userRank.isWeek();
        this.localDate = userRank.localDate;
    }

}
