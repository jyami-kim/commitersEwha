package com.jyami.commitersewha.payload.response;

import lombok.*;

/**
 * Created by jyami on 2020/10/03
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class LikeResponse {
    private long userId;
    private long postId;
    @Builder.Default
    private Long commentId = null;
    private boolean isLike;

    public static LikeResponse ofPostLike(long userId, long postId, boolean isLike) {
        return LikeResponse.builder()
                .userId(userId)
                .postId(postId)
                .isLike(isLike)
                .build();
    }

    public static LikeResponse ofCommentLike(long userId, long postId, Long commentId, boolean isLike) {
        return LikeResponse.builder()
                .userId(userId)
                .postId(postId)
                .commentId(commentId)
                .isLike(isLike)
                .build();
    }
}
