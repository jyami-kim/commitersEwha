package com.jyami.commitersewha.payload.request;

import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.projectPost.ProjectPost;
import com.jyami.commitersewha.domain.projectPost.ProjectPostCategory;
import com.jyami.commitersewha.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * Created by jyami on 2020/09/30
 */
@Getter
@NoArgsConstructor
public final class ProjectPostRequest {

    private Long projectPostId; //update 할 때 사용
    @NotNull
    private String title;
    @NotNull
    private String introducing;
    @NotNull
    private String githubLink;
    private String deployLinkIOS;
    private String deployLinkAndroid;
    private String deployLinkWeb;
    private String deployLinkOther;
    private ProjectPostCategory projectPostCategory;
    private String hashTags;
    private String devStacks;
    private String image;
    private String youtubeLink;

    public ProjectPost toEntity(User user) {
        return ProjectPost.builder()
                .title(this.title)
                .introducing(this.introducing)
                .githubLink(this.githubLink)
                .deployLinkAndroid(this.deployLinkAndroid)
                .deployLinkIOS(this.deployLinkIOS)
                .deployLinkOther(this.deployLinkOther)
                .deployLinkWeb(this.deployLinkWeb)
                .projectPostCategory(this.projectPostCategory)
                .user(user)
                .hashTags(this.hashTags)
                .devStacks(this.devStacks)
                .image(this.image)
                .youtubeLink(this.youtubeLink)
                .build();
    }

    public void updateEntity(ProjectPost projectPost) {
        projectPost.setTitle(this.title);
        projectPost.setProjectPostCategory(this.projectPostCategory);
        projectPost.setIntroducing(this.introducing);
        projectPost.setHashTags(this.hashTags);
        projectPost.setDeployLinkAndroid(this.deployLinkAndroid);
        projectPost.setDeployLinkIOS(this.deployLinkIOS);
        projectPost.setDeployLinkOther(this.deployLinkOther);
        projectPost.setDeployLinkWeb(this.deployLinkWeb);
        projectPost.setDevStacks(this.devStacks);
        projectPost.setGithubLink(this.githubLink);
        projectPost.setImage(this.image);
        projectPost.setYoutubeLink(this.youtubeLink);
    }

//    protected String joiningHashTag() {
//        return String.join(",", this.hashTags);
//    }

//    protected String joiningDevStacks() {
//        return String.join(",", this.devStacks);
//    }

}
