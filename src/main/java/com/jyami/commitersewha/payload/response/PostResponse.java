package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.domain.Post;
import com.jyami.commitersewha.domain.User;
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
    private Post.Category category;
    @Builder.Default
    private long hit = 0;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @Builder.Default
    private List<CommentResponse> commentResponses = Collections.emptyList();

    // TODO : 좋아요 기능 확장
    // TODO : 해시태그 기능 구현

    public static PostResponse fromEntityToShotDto(Post post) {
        User user = post.getUser();
        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .detail(post.getDetail())
                .userId(user.getUserId())
                .userName(user.getName())
                .category(post.getCategory())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }

    public static PostResponse fromEntityToLongDto(Post post) {
        User user = post.getUser();
        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .detail(post.getDetail())
                .userId(user.getUserId())
                .userName(user.getName())
                .category(post.getCategory())
                .hit(post.getHit())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .commentResponses(
                        post.getComments().stream()
                                .map(CommentResponse::fromEntity)
                                .collect(Collectors.toList()))
                .build();
    }

}
