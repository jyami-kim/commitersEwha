package com.jyami.commitersewha.domain.projectComment;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.projectPost.ProjectPost;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jyami on 2020/09/30
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ProjectComment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectCommentId")
    private long projectCommentId;

    private String content;

    @Builder.Default
    private long parent = -1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectPostId")
    private ProjectPost projectPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(name = "project_comment_user_like_link")
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
