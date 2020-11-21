package com.jyami.commitersewha.payload.rssFeed;

import com.jyami.commitersewha.domain.rssFeed.RssFeed;
import lombok.*;

/**
 * Created by jyami on 2020/11/21
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class RssFeedInfo {
    private String company;
    private String color;

    public static RssFeedInfo of(RssFeed rssFeed) {
        return RssFeedInfo.builder()
                .color(rssFeed.getColor())
                .company(rssFeed.getName())
                .build();
    }

}
