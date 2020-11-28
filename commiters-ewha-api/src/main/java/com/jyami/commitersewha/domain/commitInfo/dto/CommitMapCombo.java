package com.jyami.commitersewha.domain.commitInfo.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jyami on 2020/11/25
 */
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CommitMapCombo {
    long commitCount;
    long commitMaxCombo;
    long score;

    public static CommitMapCombo calculate(List<CommitMap> commitMaps) {
        commitMaps.sort(Comparator.comparing(CommitMap::getCommitDate));
        long commitCount = 0;
        long commitCombo = 0;
        long commitComboWeight = 1;
        long comboScore = 0;
        long commitMaxCombo = 0;

        LocalDate beforeDate = LocalDate.now();

        if (!commitMaps.isEmpty()) {
            CommitMap commitMap = commitMaps.get(0);
            beforeDate = commitMap.getCommitDate();
        }
        for (CommitMap commit : commitMaps) {
            commitCount += commit.getCommitCount();
            if (commitCombo % 7 == 0) {
                commitComboWeight = commitCombo / 7 + 1;
            }
            if (commit.getCommitDate().minus(1, ChronoUnit.DAYS).equals(beforeDate)) {
                comboScore += commitComboWeight * 5;
                commitCombo += 1;
                if (commitMaxCombo < commitCombo) {
                    commitMaxCombo = commitCombo;
                }
            } else {
                commitCombo = 0;
                commitComboWeight = 1;
            }
            beforeDate = commit.getCommitDate();

        }
        return CommitMapCombo.builder()
                .commitMaxCombo(commitMaxCombo)
                .commitCount(commitCount)
                .score(commitCount * 10 + comboScore)
                .build();
    }

}
