package com.jyami.commitersewha.domain.post;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.comment.CommentRepository;
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
    @Ignore
    @DisplayName("join 없이 post를 가져올 경우에 comment 내용을 읽을 때 쿼리가 많이 나가는 문제가 생긴다.")
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
            postRepository.findByPostId(1L)
                    .orElseThrow(() -> new ResourceNotFoundException("post", "postId", 1L));
        });
    }

    @Test
    @DisplayName("post를 id기반으로 가져올 떄 user도 함께 가져온다. : query 확인")
    void findPostAllById() {
        User user = settingUser();
        Post post = settingPost(user);
        settingComments(user, post);
        entityManager.clear();
        Post post1 = postRepository.findByPostId(1L)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", 1L));
        System.out.println(post1.getUser().getName());
        System.out.println(post1.getHit());
        System.out.println(post.getLikesUser().size());
    }
}