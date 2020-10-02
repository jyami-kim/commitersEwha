package com.jyami.commitersewha.payload.request;

import com.jyami.commitersewha.domain.Comment;
import com.jyami.commitersewha.domain.Post;
import com.jyami.commitersewha.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by jyami on 2020/10/02
 */
@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotNull
    private String content;

    private long parent = -1;

    @NotNull
    private long postId;

    public Comment toEntity(Post post, User user){
        return Comment.builder()
                .content(this.content)
                .post(post)
                .user(user)
                .parent(parent)
                .build();
    }

}
