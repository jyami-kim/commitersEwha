package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.commitInfo.GithubCommitInfo;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by jyami on 2020/11/30
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class GithubCommitInfoResponse {
    private String sha;
    private String htmlUrl;
    private String commitMessage;
    private LocalDateTime date;

    public static GithubCommitInfoResponse of(GithubCommitInfo commitInfo) {
        return GithubCommitInfoResponse.builder()
                .sha(commitInfo.getSha())
                .htmlUrl(commitInfo.getHtmlUrl())
                .commitMessage(commitInfo.getCommitMessage())
                .date(commitInfo.getDate())
                .build();
    }

}
