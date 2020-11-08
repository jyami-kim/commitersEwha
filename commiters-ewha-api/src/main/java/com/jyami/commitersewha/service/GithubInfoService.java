package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.githubInfo.GithubInfoRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by jyami on 2020/11/08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GithubInfoService {

    private final GithubInfoRepository githubInfoRepository;

    public GithubInfo getGithubInfoFromUserId(Long userId) {
        return githubInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("GithubInfo.userId", "id", userId));
    }

}
