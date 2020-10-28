package com.jyami.commitersewha.payload.request;

import com.google.common.collect.Sets;
import com.jyami.commitersewha.domain.tag.DevStack;
import com.jyami.commitersewha.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by jyami on 2020/09/30
 */
@Getter
@NoArgsConstructor
public final class UserUpdateRequest {
    private String description;
    @NotNull
    private String major;
    @NotNull
    private int entranceYear;
    @NotNull
    private boolean isGraduate;
    private String job;
    private String company;
    private List<Long> devStackIdList;

    public void updateUserInfo(User user, List<DevStack> devStacks) {
        user.setDescription(this.description);
        user.setMajor(this.major);
        user.setEntranceYear(this.entranceYear);
        user.setGraduate(this.isGraduate);
        user.setJob(this.job);
        user.setCompany(this.company);
        user.setDevStacks(Sets.newHashSet(devStacks));
    }
}
