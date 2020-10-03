package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.post.Category;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/09/30
 */
@NoArgsConstructor
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public final class PostResponse {

    private long postId;
    private String title;
    private String detail;
    private long userId;
    private String userName;
    private Category category;
    @Builder.Default
    private long hit = 0;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @Builder.Default
    private List<CommentResponse> commentResponses = Collections.emptyList();
    private List<String> hashTags;
    private Integer postLikeSize;

    public static PostResponse fromEntityWithCommentDto(List<Comment> comments) {
        Post post = comments.get(0).getPost();
        User user = post.getUser();

        List<CommentResponse> commentResponses = comments.stream()
                .map(CommentResponse::fromEntity)
                .collect(Collectors.toList());

        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .detail(post.getDetail())
                .userId(user.getUserId())
                .userName(user.getName())
                .category(post.getCategory())
                .hashTags(post.getHashTags())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .commentResponses(commentResponses)
                .build();
    }

    public static PostResponse fromEntityToWithoutComment(Post post) {
        User user = post.getUser();
        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .detail(post.getDetail())
                .userId(user.getUserId())
                .userName(user.getName())
                .category(post.getCategory())
                .hashTags(post.getHashTags())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .postLikeSize(post.getLikesUser().size())
                .build();
    }

}
