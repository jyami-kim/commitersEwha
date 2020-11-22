package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by jyami on 2020/11/09
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
public class GithubCommitInfo {

    @Id
    private String sha;
    private String htmlUrl;
    private String commitMessage;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infoId")
    private GithubInfo githubInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubCommitInfo that = (GithubCommitInfo) o;
        return sha.equals(that.sha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha);
    }
}
