package com.jyami.commitersewha.payload.request;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.projectComment.ProjectComment;
import com.jyami.commitersewha.domain.projectPost.ProjectPost;
import com.jyami.commitersewha.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by jyami on 2020/10/02
 */
@Getter
@NoArgsConstructor
public final class CommentRequest {

    @NotNull
    private String content;

    private long parent = -1;

    @NotNull
    private long postId;

    public Comment toPostEntity(Post post, User user) {
        return Comment.builder()
                .content(this.content)
                .post(post)
                .user(user)
                .parent(parent)
                .build();
    }

    public ProjectComment toProjectPostEntity(ProjectPost projectPost, User user) {
        return ProjectComment.builder()
                .content(this.content)
                .projectPost(projectPost)
                .user(user)
                .parent(parent)
                .build();
    }

}
