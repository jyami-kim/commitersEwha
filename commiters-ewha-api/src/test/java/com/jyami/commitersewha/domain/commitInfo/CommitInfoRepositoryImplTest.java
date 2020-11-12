package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.comment.CommentRepository;
import com.jyami.commitersewha.domain.post.PostRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.domain.user.UserRepository;
import com.jyami.commitersewha.preSetting.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/11/12
 */
@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class CommitInfoRepositoryImplTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CommitInfoRepository commitInfoRepository;

    private void commit() {

    }

    @Test
    void findBetweenTime() {
    }
}