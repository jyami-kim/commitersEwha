package com.jyami.commitersewha.config.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
