package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.Comment;
import com.jyami.commitersewha.domain.Post;
import com.jyami.commitersewha.domain.User;
import lombok.*;


/**
 * Created by jyami on 2020/10/02
 */
@NoArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponse {

    private long commentId;

    private String content;

    private long parent;

    private long postId;

    private long userId;

    public static CommentResponse fromEntity(Comment comment){
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .parent(comment.getParent())
                .postId(comment.getPost().getPostId())
                .userId(comment.getUser().getUserId())
                .build();

    }
}
