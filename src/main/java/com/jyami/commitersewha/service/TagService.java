package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.Badge;
import com.jyami.commitersewha.domain.BadgeRepository;
import com.jyami.commitersewha.domain.DevStack;
import com.jyami.commitersewha.domain.DevStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jyami on 2020/09/30
 */
@Service
@RequiredArgsConstructor
public class TagService {

    private final DevStackRepository devStackRepository;
    private final BadgeRepository badgeRepository;

    public List<DevStack> getAllDevStacks() {
        return devStackRepository.findAll();
    }

    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    public List<Badge> findAllByBadgeId(List<Long> badgeIds) {
        return badgeRepository.findAllById(badgeIds);
    }

    public List<DevStack> findAllBtDevStackId(List<Long> devStacks) {
        return devStackRepository.findAllById(devStacks);
    }
}
