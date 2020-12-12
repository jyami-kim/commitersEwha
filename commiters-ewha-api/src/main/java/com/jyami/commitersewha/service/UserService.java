package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.domain.user.UserRepository;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.request.UserUpdateRequest;
import com.jyami.commitersewha.payload.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by jyami on 2020/09/22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getCurrentUser(Long userId) {
        return UserInfoResponse.fromEntity(getUserFromId(userId));
    }

    @Transactional
    public UserInfoResponse nextSignUp(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = getUserFromId(userId);
        userUpdateRequest.updateUserInfo(user);
        return UserInfoResponse.fromEntity(userRepository.save(user));
    }

    public User getUserFromId(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public User getUserFromSubId(String userSubId){
        return userRepository.findBySubId(userSubId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "subId", userSubId));
    }


}
