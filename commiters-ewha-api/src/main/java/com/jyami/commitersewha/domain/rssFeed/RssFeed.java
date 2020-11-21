package com.jyami.commitersewha.domain.rssFeed;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by jyami on 2020/10/08
 */
@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RssFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rssId")
    private long rssId;

    private String url;

    private String name;

    private String color;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private FeedType feedType;

}
