package com.jyami.commitersewha.domain.tag;

import lombok.*;

import javax.persistence.*;

/**
 * Created by jyami on 2020/09/30
 */
@Entity
@Getter
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badgeId")
    private long badgeId;

    private String title;
    private String description;
    private String image;

}
