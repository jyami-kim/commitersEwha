package com.jyami.commitersewha.domain.post;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.user.User;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

/**
 * Created by jyami on 2020/09/30
 */

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private long postId;

    @Setter
    private String title;
    @Setter
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @Setter
    private Category category;

    @Builder.Default
    private long hit = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
    @Builder.Default
    private Set<Comment> comments = new HashSet<>();

    @Setter
    private String hashTags;

    @ManyToMany
    @JoinTable(name = "post_user_like_link")
    @Builder.Default
    private Set<User> likesUser = new HashSet<>();

    public Set<User> getLikesUser() {
        return Collections.unmodifiableSet(likesUser);
    }

    public List<String> getHashTags() {
        if (StringUtils.isNotBlank(this.hashTags))
            return Arrays.asList(hashTags.split(","));
        return Collections.emptyList();
    }

    public void addHitCount() {
        this.hit = ++hit;
    }

    public void addLikeUser(User user) {
        likesUser.add(user);
    }

    public void removeLikeUser(User user) {
        likesUser.remove(user);
    }

}
