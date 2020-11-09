package com.jyami.commitersewha.githubRestTemplate.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jyami.commitersewha.domain.commitInfo.GithubCommitInfo;
import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by jyami on 2020/11/02
 */
@ToString
@Getter
@NoArgsConstructor
@JsonIgnoreProperties
public class GithubCommitResponse {

    private static final int MAX_COMMIT_MESSAGE_SIZE = 250;

    @JsonProperty("html_url")
    private String htmlUrl;
    private Commit commit;
    @JsonProperty("node_id")
    private String nodeId;

    @ToString
    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties
    public static class Commit {

        private String message;
        private Author author;

        @ToString
        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties
        public static class Author {
            private LocalDateTime date;
        }
    }

    public GithubCommitInfo toEntity(GithubInfo githubInfo) {
        return GithubCommitInfo.builder()
                .commitMessage(resizeCommitMessage())
                .nodeId(this.nodeId)
                .htmlUrl(this.htmlUrl)
                .githubInfo(githubInfo)
                .date(this.commit.author.date)
                .build();
    }

    private String resizeCommitMessage() {
        String commitMessage = this.commit.message;
        if (commitMessage.length() > MAX_COMMIT_MESSAGE_SIZE)
            return commitMessage.substring(0, MAX_COMMIT_MESSAGE_SIZE);
        return commitMessage;
    }
}
