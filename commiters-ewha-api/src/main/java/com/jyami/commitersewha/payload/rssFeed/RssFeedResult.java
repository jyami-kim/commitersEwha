package com.jyami.commitersewha.payload.rssFeed;

import com.jyami.commitersewha.domain.rssFeed.FeedType;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/10/08
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class RssFeedResult {
    private String blogName;
    @Builder.Default
    private List<RssFeedContents> feedContents = Collections.emptyList();
    private boolean isError;
    private FeedType feedType;

    public static RssFeedResult parsing(SyndFeed feed, FeedType feedType) {
        List<RssFeedContents> contents = feed.getEntries().stream()
                .map(RssFeedContents::of)
                .collect(Collectors.toList());

        return RssFeedResult.builder()
                .blogName(feed.getTitle())
                .feedContents(contents)
                .feedType(feedType)
                .isError(false)
                .build();
    }

    public static RssFeedResult parsingError(String blogName) {
        return RssFeedResult.builder()
                .blogName(blogName)
                .isError(true)
                .build();
    }
}
