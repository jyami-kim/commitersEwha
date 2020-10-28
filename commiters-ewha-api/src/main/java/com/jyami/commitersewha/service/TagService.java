package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.tag.Badge;
import com.jyami.commitersewha.domain.tag.BadgeRepository;
import com.jyami.commitersewha.domain.tag.DevStack;
import com.jyami.commitersewha.domain.tag.DevStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public List<DevStack> findAllBtDevStackId(Set<Long> devStacks) {
        return devStackRepository.findAllById(devStacks);
    }
}
