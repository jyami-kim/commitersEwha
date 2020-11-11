package com.jyami.commitersewha.githubRestTemplate.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by jyami on 2020/11/02
 */

@ToString
@Getter
@NoArgsConstructor
@JsonIgnoreProperties
public class CommitStatisticResponse {
    @JsonProperty("weeks")
    private List<Statistic> statistics;
    private Author author;
    private Long total;

    @ToString
    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties
    public static class Statistic{
        @JsonProperty("w")
        private long week; //UNIX Time
        @JsonProperty("a")
        private long additions;
        @JsonProperty("d")
        private long deletions;
        @JsonProperty("c")
        private long commits;
    }

    @ToString
    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties
    public static class Author {
        private String login;
        private Long id;
    }
}
