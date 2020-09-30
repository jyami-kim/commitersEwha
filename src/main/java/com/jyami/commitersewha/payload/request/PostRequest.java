package com.jyami.commitersewha.payload.request;

import com.jyami.commitersewha.domain.DevStack;
import com.jyami.commitersewha.domain.Post;
import com.jyami.commitersewha.domain.User;
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
public class PostRequest {

    private Long postId; //update 할 때 사용
    @NotNull
    private String title;
    @NotNull
    private String detail;
    private List<Long> devStackList = Collections.emptyList();
    @NotNull
    private Post.Category category;
    // TODO : 해시태그 기능 확장

    public Post toEntity(User user, List<DevStack> devStacks){
        return Post.builder()
                .title(this.title)
                .detail(this.detail)
                .category(this.category)
                .user(user)
                .devStackList(devStacks)
                .build();
    }

    public void updateEntity(Post post,List<DevStack> devStacks){
        post.setTitle(this.title);
        post.setCategory(this.category);
        post.setDetail(this.detail);
        post.setDevStackList(devStacks);
    }

}
