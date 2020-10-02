package com.jyami.commitersewha.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    // TODO : 좋아요 기능 확장

//    @ManyToMany
//    @JoinTable(name = "post_dev_stack_link")
//    @Setter
//    @Builder.Default
    // TODO : 해시태그 기능 확장

    @Getter
    public enum Category {
        CHAT("잡담"), EMPLOYMENT("취업/이직"), REVIEW("후기"), ADVERTISING("홍보");

        Category(String name) {
            this.name = name;
        }

        private String name;
    }

    public void addHitCount() {
        this.hit = ++hit;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
