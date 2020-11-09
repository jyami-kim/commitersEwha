package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import lombok.*;

/**
 * Created by jyami on 2020/11/09
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class GithubDetailInfoResponse {

    private String name;
    private String email;
    private String imageUrl;
    private String providerId;
    private String authorId;

    public static GithubDetailInfoResponse fromEntity(GithubInfo githubInfo) {
        return GithubDetailInfoResponse.builder()
                .name(githubInfo.getName())
                .email(githubInfo.getEmail())
                .imageUrl(githubInfo.getImageUrl())
                .authorId(githubInfo.getAuthorId())
                .providerId(githubInfo.getProviderId()).build();
    }

}
