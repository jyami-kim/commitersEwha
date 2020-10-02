package com.jyami.commitersewha.domain.post;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/09/30
 */

@Entity
@Getter
@NoArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @Setter
    private String hashTags;

    // TODO : 좋아요 기능 확장

    public List<String> getHashTags(){
        return Arrays.asList(hashTags.split(","));
    }

    public void addHitCount() {
        this.hit = ++hit;
    }

}
