package com.jyami.commitersewha.domain;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(name = "post_dev_stack_link")
    @Setter
    private List<DevStack> devStackList = Collections.emptyList();

    // TODO : 해시태그 기능 확장

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @Setter
    private Category category;

    private long hit = 0;

    // TODO : 좋아요 기능 확장

    // TODO : 댓글 기능 확장


    @Getter
    public enum Category {
        CHAT("잡담"), EMPLOYMENT("취업/이직"), REVIEW("후기"), ADVERTISING("홍보");

        Category(String name) {
            this.name = name;
        }

        private String name;
    }

    public void addHitCount(){
        this.hit = ++hit;
    }
}
