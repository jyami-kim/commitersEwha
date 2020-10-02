package com.jyami.commitersewha.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/09/30
 */
@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class BadgeRepositoryTest {

    @Autowired
    private BadgeRepository badgeRepository;

    @BeforeEach
    void setUp() {
        Badge badge1 = Badge.builder().title("테스트1").description("1").build();
        Badge badge2 = Badge.builder().title("테스트2").description("2").build();
        Badge badge3 = Badge.builder().title("테스트3").description("3").build();
        List<Badge> badges = Arrays.asList(badge1, badge2, badge3);
        badgeRepository.saveAll(badges);
    }

    @Test
    @Ignore
    // BeforeEach 이기 때문에 Id 기반 findAll 이 전체에서 돌릴 때 fail이 난다.
    @DisplayName("뱃지 아이디 리스트에따른 뱃지들 리스트를 가져오는 걸 성공한다.")
    void getAllBadgeIds() {
        List<Badge> allById = badgeRepository.findAllById(Arrays.asList(1L, 3L));
        assertThat(allById.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("없는 뱃지 아이디가 같이 있을 때 있는 것만 가져온다.")
    void getAllBadgeIdsWithWrongId() {
        List<Badge> allById = badgeRepository.findAllById(Arrays.asList(1L, 4L));
        assertThat(allById.size()).isEqualTo(1);
    }
}