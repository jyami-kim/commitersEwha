package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.projectComment.ProjectComment;
import lombok.*;

import java.time.LocalDateTime;


/**
 * Created by jyami on 2020/10/02
 */
@NoArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CommentResponse {

    private long commentId;

    private String content;

    private long parent;

    private long postId;

    private long userId;

    private String userName;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private Integer commentLikeSize;

    public static CommentResponse fromPostEntity(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .parent(comment.getParent())
                .postId(comment.getPost().getPostId())
                .userId(comment.getUser().getUserId())
                .userName(comment.getUser().getName())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .commentLikeSize(comment.getLikesUser().size())
                .build();
    }

    public static CommentResponse fromProjectPostEntity(ProjectComment projectComment) {
        return CommentResponse.builder()
                .commentId(projectComment.getProjectCommentId())
                .content(projectComment.getContent())
                .parent(projectComment.getParent())
                .postId(projectComment.getProjectPost().getProjectPostId())
                .userId(projectComment.getUser().getUserId())
                .userName(projectComment.getUser().getName())
                .createdDate(projectComment.getCreatedDate())
                .modifiedDate(projectComment.getModifiedDate())
                .commentLikeSize(projectComment.getLikesUser().size())
                .build();
    }
}
