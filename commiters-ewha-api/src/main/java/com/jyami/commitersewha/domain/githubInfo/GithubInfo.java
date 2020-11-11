package com.jyami.commitersewha.domain.githubInfo;

import com.jyami.commitersewha.domain.BaseTime;
import com.jyami.commitersewha.domain.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jyami on 2020/10/12
 */
@Entity
@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
public class GithubInfo extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long infoId;

    private long id;

    @Setter
    private String name;

    private String authorId;

    private String email;

    @Setter
    private String imageUrl;

    private Long providerId;

    @Setter
    private String token;

    @OneToOne
    @JoinColumn(name = "userId")
    @NotNull
    private User user;

    public String getToken() {
        return "token " + token;
    }

}
