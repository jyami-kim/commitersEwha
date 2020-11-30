package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.userRank.UserRank;
import lombok.*;

import java.time.LocalDate;

/**
 * Created by jyami on 2020/11/28
 */
@NoArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRankInfoResponse {

    private long githubInfoId;

    private String authorId;

    private long score;

    private long commitCount;

    private long commitMaxCombo;

    private boolean week;

    private LocalDate localDate;

    public static UserRankInfoResponse of(UserRank userRank, GithubInfo githubInfo) {
        return UserRankInfoResponse.builder()
                .githubInfoId(githubInfo.getInfoId())
                .authorId(githubInfo.getAuthorId())
                .score(userRank.getScore())
                .commitCount(userRank.getCommitCount())
                .commitMaxCombo(userRank.getCommitMaxCombo())
                .week(userRank.isWeek())
                .localDate(userRank.getLocalDate())
                .build();
    }
}
