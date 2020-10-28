package com.jyami.commitersewha.domain.comment;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * Created by jyami on 2020/09/30
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private long commentId;

    private String content;

    @Builder.Default
    private long parent = -1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(name = "comment_user_like_link")
    @Builder.Default
    private Set<User> likesUser = new HashSet<>();

    public Set<User> getLikesUser() {
        return Collections.unmodifiableSet(likesUser);
    }

    public void addLikeUser(User user) {
        likesUser.add(user);
    }

    public void removeLikeUser(User user) {
        likesUser.remove(user);
    }
}
