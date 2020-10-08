package com.jyami.commitersewha.payload.rssFeed;

import com.rometools.rome.feed.synd.SyndEntry;
import lombok.*;

/**
 * Created by jyami on 2020/10/08
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class RssFeedContents {
    private String title;
    private String link;

    public static RssFeedContents of(SyndEntry entry){
        return RssFeedContents.builder()
                .title(entry.getTitle())
                .link(entry.getLink())
                .build();
    }
}
