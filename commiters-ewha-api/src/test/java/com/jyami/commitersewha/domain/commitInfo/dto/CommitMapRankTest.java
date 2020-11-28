package com.jyami.commitersewha.domain.commitInfo.dto;

import com.jyami.commitersewha.domain.userRank.dto.CommitMapRank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/11/28
 */
class CommitMapRankTest {


    public List<CommitMap> getSampleCommitMap() {
        List<CommitMap> commitMaps = new ArrayList<>();
        commitMaps.add(new CommitMap(1L, "2020-08-30"));
        commitMaps.add(new CommitMap(1L, "2020-08-29"));
        commitMaps.add(new CommitMap(1L, "2020-08-28"));
        commitMaps.add(new CommitMap(1L, "2020-08-27"));
        commitMaps.add(new CommitMap(1L, "2020-08-26"));
        commitMaps.add(new CommitMap(1L, "2020-08-25"));
        commitMaps.add(new CommitMap(1L, "2020-08-24"));
        commitMaps.add(new CommitMap(1L, "2020-08-23"));
        commitMaps.add(new CommitMap(1L, "2020-08-22"));
        commitMaps.add(new CommitMap(1L, "2020-08-21"));
        commitMaps.add(new CommitMap(1L, "2020-08-20"));
        commitMaps.add(new CommitMap(1L, "2020-08-19"));
        commitMaps.add(new CommitMap(1L, "2020-08-18"));
        commitMaps.add(new CommitMap(1L, "2020-08-17"));
        commitMaps.add(new CommitMap(1L, "2020-08-16"));
        commitMaps.add(new CommitMap(1L, "2020-08-15"));
        commitMaps.add(new CommitMap(1L, "2020-08-14"));
        commitMaps.add(new CommitMap(1L, "2020-08-13"));

        return commitMaps;
    }

    @Test
    void calculate() throws IOException {
        CommitMapRank calculate = CommitMapRank.calculate(getSampleCommitMap());
        System.out.println(calculate);
        assertThat(calculate.getScore()).isEqualTo(330);
        assertThat(calculate.getCommitCount()).isEqualTo(18);
        assertThat(calculate.getCommitMaxCombo()).isEqualTo(17);
    }
}