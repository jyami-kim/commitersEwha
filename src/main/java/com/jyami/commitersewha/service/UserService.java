package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.Badge;
import com.jyami.commitersewha.domain.DevStack;
import com.jyami.commitersewha.domain.User;
import com.jyami.commitersewha.domain.UserRepository;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.request.UserSignupRequest;
import com.jyami.commitersewha.payload.response.UserInfoResponse;
import com.jyami.commitersewha.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by jyami on 2020/09/22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TagService tagService;

    public User getCurrentUser(Long userId) {
        return getUserFromId(userId);
    }

    @Transactional
    public User nextSignUp(Long userId, UserSignupRequest userSignupRequest) {
        User user = getUserFromId(userId);
        List<Badge> allBadges = tagService.findAllByBadgeId(userSignupRequest.getBadgeIdList());
        List<DevStack> allDevStacks = tagService.findAllBtDevStackId(userSignupRequest.getDevStackIdList());
        userSignupRequest.updateUserInfo(user, allBadges, allDevStacks);
        return userRepository.save(user);
    }

    private User getUserFromId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }


}
