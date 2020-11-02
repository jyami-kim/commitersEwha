package com.jyami.commitersewha.config.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserDetailResponse {
    private String login;
    private Long id;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    @JsonProperty("twitter_username")
    private String twitterUsername;
    @JsonProperty("public_repos")
    private Long publicRepos;
    @JsonProperty("public_gists")
    private Long publicGists;
    private Long followers;
    private Long following;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
