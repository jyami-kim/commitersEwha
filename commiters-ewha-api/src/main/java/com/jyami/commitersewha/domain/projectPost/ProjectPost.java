package com.jyami.commitersewha.domain.projectPost;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.projectComment.ProjectComment;
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
public class ProjectPost extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectPostId")
    private long projectPostId;

    @Setter
    private String title;
    @Setter
    private String introducing;
    @Setter
    private String githubLink;
    @Setter
    private String deployLinkIOS;
    @Setter
    private String deployLinkAndroid;
    @Setter
    private String deployLinkWeb;
    @Setter
    private String deployLinkOther;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @Setter
    private ProjectPostCategory projectPostCategory;

    @Builder.Default
    private long hit = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectPost", cascade = CascadeType.REMOVE)
    @Builder.Default
    private Set<ProjectComment> comments = new HashSet<>();

    @Setter
    private String devStacks;

    @Setter
    private String hashTags;
    @Setter
    private String image;
    @Setter
    private String youtubeLink;

    @ManyToMany
    @JoinTable(name = "project_post_user_like_link")
    @Builder.Default
    private Set<User> likesUser = new HashSet<>();

    public Set<User> getLikesUser() {
        return Collections.unmodifiableSet(likesUser);
    }

    public List<String> getDevStacks() {
        if (StringUtils.isNotBlank(this.devStacks))
            return Arrays.asList(devStacks.split(","));
        return Collections.emptyList();
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
