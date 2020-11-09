package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commitId;
    private String htmlUrl;
    private String commitMessage;
    private String nodeId;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infoId")
    private GithubInfo githubInfo;

}
