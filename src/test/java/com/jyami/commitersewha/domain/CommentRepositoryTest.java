package com.jyami.commitersewha.domain;

import com.jyami.commitersewha.preSetting.TestConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/10/02
 */
@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
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
                .category(Post.Category.ADVERTISING)
                .detail("세부사항")
                .title("제목")
                .user(settingUser)
                .build();

        return postRepository.save(settingPost);
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