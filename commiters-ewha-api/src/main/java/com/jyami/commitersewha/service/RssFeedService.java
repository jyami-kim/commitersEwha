package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.rssFeed.FeedType;
import com.jyami.commitersewha.domain.rssFeed.RssFeed;
import com.jyami.commitersewha.domain.rssFeed.RssFeedRepository;
import com.jyami.commitersewha.exception.FeedParsingException;
import com.jyami.commitersewha.payload.rssFeed.RssFeedContents;
import com.jyami.commitersewha.payload.rssFeed.RssFeedParser;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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

    public List<RssFeedContents> getAllRssFeedResult() {
        List<RssFeed> rssFeeds = rssFeedRepository.findAll();
        return rssFeeds.stream()
                .map(f -> {
                    try {
                        SyndFeed feed = RssFeedParser.parsingEntry(f.getUrl());
                        return parsing(feed, f.getName());
                    } catch (FeedParsingException e) {
                        log.error("rss parsing Error {} : {}", f.getName(), f.getUrl());
                        return parsingError();
                    }
                })
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<RssFeedContents> parsing(SyndFeed feed, String company) {
        return feed.getEntries().stream()
                .map(entry -> RssFeedContents.of(company, entry))
                .collect(Collectors.toList());
    }

    public static List<RssFeedContents> parsingError() {
        return Collections.emptyList();
    }

}
