package com.jyami.commitersewha.domain;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.comment.CommentRepository;
import com.jyami.commitersewha.domain.post.Category;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.post.PostRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.domain.user.UserRepository;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.preSetting.TestConfig;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/10/02
 */
@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
class PostRepositoryTest {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    private User settingUser() {
//        userRepository.deleteAll();
        User settingUser = User.builder()
                .email("jyami@ewhain.net")
                .name("jyami")
                .subId("jyami")
                .role(User.Role.USER)
                .build();

        return userRepository.save(settingUser);
    }

    private Post settingPost(User settingUser) {
        Post settingPost = Post.builder()
                .category(Category.ADVERTISING)
                .detail("세부사항")
                .title("제목")
                .user(settingUser)
                .build();

        return postRepository.save(settingPost);
    }

    private void settingComments(User user, Post post) {
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content("댓글 예시")
                .build();

        Comment comment2 = Comment.builder()
                .user(user)
                .post(post)
                .content("댓글 예시")
                .build();

        Comment comment3 = Comment.builder()
                .user(user)
                .post(post)
                .content("댓글 예시")
                .build();

        commentRepository.saveAll(Arrays.asList(comment, comment2, comment3));
    }


    @Test
    @DisplayName("join 없이 post를 가져올 경우에 comment 내용을 읽을 때 문제가 생긴다.")
    void name() {
        User user = settingUser();
        Post post = settingPost(user);
        settingComments(user, post);
        entityManager.clear();

        Optional<Post> savedPost = postRepository.findById(1L);
        for (Comment comment : savedPost.get().getComments()) {
            System.out.println(comment.toString());
        }
    }

    @Test
    @DisplayName("postID가 존재하지 않는경우 에러를 발생한다.")
    void findPostByIdWithCommentsTestIfHaveNotPost() {
        assertThrows(ResourceNotFoundException.class, () -> {
            postRepository.findPostByIdWithComments(1L)
                    .orElseThrow(() -> new ResourceNotFoundException("post", "postId", 1L));
        });
    }

    @Test
    @DisplayName("join을 이용하여 데이터베이스 N+1 문제를 방지한다.")
    void findPostByIdWithCommentsTest() {
        User user = settingUser();
        Post post = settingPost(user);
        settingComments(user, post);
        entityManager.clear();

        Post post1 = postRepository.findPostByIdWithComments(1L).get();
        for (Comment comment : post1.getComments()) {
            System.out.println(comment.toString());
            System.out.println(comment.getUser().toString());
        }
    }

    // TODO : User fetchjoin을 아직하지 못했다.
    @Test
    @Ignore
    @DisplayName("N+1 문제 방지를 위해 comment 가져올 때 user도 같이 가져온다.")
    void findPostByIdWithCommentsTest2() {
        User user = settingUser();
        Post post = settingPost(user);
        settingComments(user, post);
        entityManager.clear();

        List<Comment> postByIdWithComments2 = postRepository.findPostByIdWithComments2(1L);
        for (Comment comment : postByIdWithComments2) {
            System.out.println(comment.toString());
            System.out.println(comment.getUser().toString());
        }
    }
}