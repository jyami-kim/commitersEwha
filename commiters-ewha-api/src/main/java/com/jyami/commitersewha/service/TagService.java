package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.tag.Badge;
import com.jyami.commitersewha.domain.tag.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jyami on 2020/09/30
 */
@Service
@RequiredArgsConstructor
public class TagService {

    private final BadgeRepository badgeRepository;

    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    public List<Badge> findAllByBadgeId(List<Long> badgeIds) {
        return badgeRepository.findAllById(badgeIds);
    }

}
