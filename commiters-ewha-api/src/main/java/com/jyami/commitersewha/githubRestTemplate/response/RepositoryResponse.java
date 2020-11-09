package com.jyami.commitersewha.githubRestTemplate.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.githubRepoInfo.GithubRepoInfo;
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
public class RepositoryResponse {
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String description;
    private boolean fork;
    private String language;
    @JsonProperty("stargazers_count")
    private int stargazersCount;
    @JsonProperty("watchers_count")
    private int watchersCount;
    @JsonProperty("forks_count")
    private int forksCount;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public GithubRepoInfo toEntity(GithubInfo githubInfo) {
        return GithubRepoInfo.builder()
                .name(this.name)
                .owner(owner())
                .htmlUrl(this.htmlUrl)
                .description(this.description)
                .language(this.language)
                .stargazersCount(this.stargazersCount)
                .watchersCount(this.watchersCount)
                .forksCount(this.forksCount)
                .githubInfo(githubInfo)
                .build();
    }

    private String owner() {
        return this.fullName.split("/")[0];
    }
}
