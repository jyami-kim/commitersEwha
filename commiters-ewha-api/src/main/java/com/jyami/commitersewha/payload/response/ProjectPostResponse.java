package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.post.PostCategory;
import com.jyami.commitersewha.domain.projectPost.ProjectPost;
import com.jyami.commitersewha.domain.projectPost.ProjectPostCategory;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/09/30
 */
@NoArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public final class ProjectPostResponse {

    private long postId;
    private String title;
    private String introducing;
    private String githubLink;
    private String deployLinkIOS;
    private String deployLinkAndroid;
    private String deployLinkWeb;
    private String deployLinkOther;
    private long userId;
    private String userName;
    private String userProfileUrl;
    private ProjectPostCategory projectPostCategory;
    @Builder.Default
    private long hit = 0;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String hashTags;
    private String devStacks;
    private String image;
    private String youtubeLink;
    private Integer projectPostLikeSize;

    public static ProjectPostResponse fromEntityToWithoutComment(ProjectPost projectPost) {
        User user = projectPost.getUser();
        return ProjectPostResponse.builder()
                .postId(projectPost.getProjectPostId())
                .title(projectPost.getTitle())
                .introducing(projectPost.getIntroducing())
                .githubLink(projectPost.getGithubLink())
                .deployLinkIOS(projectPost.getDeployLinkIOS())
                .deployLinkAndroid(projectPost.getDeployLinkAndroid())
                .deployLinkWeb(projectPost.getDeployLinkWeb())
                .deployLinkOther(projectPost.getDeployLinkOther())
                .userId(user.getUserId())
                .userName(user.getName())
                .userProfileUrl(projectPost.getUser().getImageUrl())
                .projectPostCategory(projectPost.getProjectPostCategory())
                .hit(projectPost.getHit())
                .createdDate(projectPost.getCreatedDate())
                .modifiedDate(projectPost.getModifiedDate())
                .image(projectPost.getImage())

                .hashTags(projectPost.getHashTags())
                .devStacks(projectPost.getDevStacks())
                .projectPostLikeSize(projectPost.getLikesUser().size())
                .youtubeLink(projectPost.getYoutubeLink())
                .build();
    }

}
