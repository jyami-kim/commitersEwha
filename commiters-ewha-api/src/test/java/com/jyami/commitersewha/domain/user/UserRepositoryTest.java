package com.jyami.commitersewha.domain.user;

import com.jyami.commitersewha.domain.tag.Badge;
import com.jyami.commitersewha.domain.tag.BadgeRepository;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.preSetting.TestConfig;
import org.assertj.core.util.Sets;
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

/**
 * Created by jyami on 2020/10/03
 */
@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private DevStackRepository devStackRepository;


    private User settingUser() {

        Badge badge1 = Badge.builder().title("테스트1").description("1").build();
        Badge badge2 = Badge.builder().title("테스트2").description("2").build();
        Badge badge3 = Badge.builder().title("테스트3").description("3").build();
        List<Badge> badges = Arrays.asList(badge1, badge2, badge3);
        badgeRepository.saveAll(badges);

        DevStack devStack1 = DevStack.builder().title("JAVA").build();
        DevStack devStack2 = DevStack.builder().title("C").build();
        DevStack devStack3 = DevStack.builder().title("Python").build();
        List<DevStack> devStacks = Arrays.asList(devStack1, devStack2, devStack3);
        devStackRepository.saveAll(devStacks);

        User settingUser = User.builder()
                .email("jyami@ewhain.net")
                .name("jyami")
                .subId("jyami")
                .role(User.Role.USER)
                .devStacks(Sets.newHashSet(Arrays.asList(new DevStack(), new DevStack(), new DevStack())))
                .badges(Sets.newHashSet(badges))
                .build();

        return userRepository.save(settingUser);
    }

    @Test
    @DisplayName("join과 함께 user정보를 조회한다 : devStack, badge join - query보기")
    void name() {
        settingUser();
        entityManager.clear();
        User user1 = userRepository.findAll().get(0);
        System.out.println(user1);
        User user = userRepository.findByUserId(1L)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", 1L));
        System.out.println(user.getBadges());
        System.out.println(user.getDevStacks());
        System.out.println(user);
    }



}