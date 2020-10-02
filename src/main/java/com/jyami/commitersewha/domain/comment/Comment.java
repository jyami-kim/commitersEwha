package com.jyami.commitersewha.domain.comment;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;

import javax.persistence.*;

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

}
