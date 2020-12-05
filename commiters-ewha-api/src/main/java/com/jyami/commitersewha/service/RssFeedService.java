package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.rssFeed.RssFeed;
import com.jyami.commitersewha.domain.rssFeed.RssFeedRepository;
import com.jyami.commitersewha.exception.FeedParsingException;
import com.jyami.commitersewha.payload.response.RssFeedResponse;
import com.jyami.commitersewha.payload.rssFeed.RssFeedContents;
import com.jyami.commitersewha.payload.rssFeed.RssFeedInfo;
import com.jyami.commitersewha.payload.rssFeed.RssFeedParser;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/10/08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RssFeedService {

    private final RssFeedRepository rssFeedRepository;

    public RssFeedResponse getAllRssFeedResult() {
        List<RssFeed> rssFeeds = rssFeedRepository.findAll();

        List<RssFeedInfo> rssFeedInfos = rssFeeds.stream()
                .map(RssFeedInfo::of)
                .collect(Collectors.toList());

        List<RssFeedContents> rssFeedContents = rssFeeds.stream()
                .map(f -> {
                    try {
                        SyndFeed feed = RssFeedParser.parsingEntry(f.getUrl());
                        return parsing(feed, f);
                    } catch (FeedParsingException e) {
                        log.error("rss parsing Error {} : {}", f.getName(), f.getUrl());
                        return parsingError();
                    }
                })
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());

        return new RssFeedResponse(rssFeedInfos, rssFeedContents);
    }

    public static List<RssFeedContents> parsing(SyndFeed feed, RssFeed rssFeed) {
        return feed.getEntries().stream()
                .map(entry -> RssFeedContents.
                        of(rssFeed, entry))
                .collect(Collectors.toList());
    }

    public static List<RssFeedContents> parsingError() {
        return Collections.emptyList();
    }

}
