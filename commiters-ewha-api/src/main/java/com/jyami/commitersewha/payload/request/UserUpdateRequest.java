package com.jyami.commitersewha.payload.request;

import com.jyami.commitersewha.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by jyami on 2020/09/30
 */
@Getter
@NoArgsConstructor
public final class UserUpdateRequest {
    private String description;
    private int entranceYear;
    private boolean isGraduate;
    private String job;
    private String company;
    private String devStacks;
    private String wantJob1;
    private String wantJob2;
    private String wantJob3;
    private String github;
    private String site;

    public void updateUserInfo(User user) {
        user.setDescription(this.description);
        user.setEntranceYear(this.entranceYear);
        user.setGraduate(this.isGraduate);
        user.setJob(this.job);
        user.setCompany(this.company);
        user.setDevStacks(this.devStacks);
        user.setWantJob1(this.wantJob1);
        user.setWantJob2(this.wantJob2);
        user.setWantJob3(this.wantJob3);
        user.setGithub(this.github);
        user.setSite(this.site);
    }
}
