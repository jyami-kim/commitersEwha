package com.jyami.commitersewha.domain.comment;

import com.jyami.commitersewha.domain.post.PostCategory;
import com.jyami.commitersewha.domain.post.Post;
import com.jyami.commitersewha.domain.post.PostRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.domain.user.UserRepository;
import com.jyami.commitersewha.preSetting.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/10/02
 */
@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class CommentRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

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
                .postCategory(PostCategory.ADVERTISING)
                .detail("세부사항")
                .title("제목")
                .user(settingUser)
                .build();

        return postRepository.save(settingPost);
    }

    @Test
//    @Ignore
    @DisplayName("N+1 문제 방지를 위해 post가져올 때 comment와 user 모두 같이 가져온다. : query확")
    void findPostByIdWithCommentsTest2() {
        User user = settingUser();
        Post post = settingPost(user);
        settingComments(user, post);
        entityManager.clear();
        List<Comment> postByIdWithComments2 = commentRepository.findPostByIdWithComments(1L);
        for (Comment comment : postByIdWithComments2) {
            System.out.println(comment.toString());
            System.out.println(comment.getUser().toString());
            System.out.println(comment.getPost().getLikesUser().size());
        }
    }

    @Test
    @DisplayName("comment를 저장했을 때 post를 조회해도 연관관계가 걸려있다.")
    void saveComment() {
        User user = settingUser();
        Post post = settingPost(user);

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

        commentRepository.saveAll(Arrays.asList(comment, comment2));

        entityManager.clear();

        Post savedPost = postRepository.findAll().get(0);
        assertThat(savedPost.getComments().size()).isEqualTo(2);
    }
}