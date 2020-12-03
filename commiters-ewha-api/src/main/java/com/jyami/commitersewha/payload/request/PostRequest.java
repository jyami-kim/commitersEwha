package com.jyami.commitersewha.payload.request;

import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.post.PostCategory;
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
public final class PostRequest {

    private Long postId; //update 할 때 사용
    @NotNull
    private String title;
    @NotNull
    private String detail;
    @NotNull
    private PostCategory postCategory;
    private List<String> hashTag = Collections.emptyList();

    public Post toEntity(User user) {
        return Post.builder()
                .title(this.title)
                .detail(this.detail)
                .postCategory(this.postCategory)
                .user(user)
                .hashTags(joiningHashTag())
                .build();
    }

    public void updateEntity(Post post) {
        post.setTitle(this.title);
        post.setPostCategory(this.postCategory);
        post.setDetail(this.detail);
        post.setHashTags(joiningHashTag());
    }

    protected String joiningHashTag() {
        return String.join(",", this.hashTag);
    }

}
