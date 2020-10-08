package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.rssFeed.RssFeed;
import com.jyami.commitersewha.domain.rssFeed.RssFeedRepository;
import com.jyami.commitersewha.exception.FeedParsingException;
import com.jyami.commitersewha.payload.rssFeed.RssFeedParser;
import com.jyami.commitersewha.payload.rssFeed.RssFeedResult;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public List<RssFeedResult> getAllRssFeedResult() {
        List<RssFeed> rssFeeds = rssFeedRepository.findAll();
        return rssFeeds.stream()
                .map(f -> {
                    try {
                        SyndFeed feed = RssFeedParser.parsingEntry(f.getUrl());
                        return RssFeedResult.parsing(feed, f.getFeedType());
                    } catch (FeedParsingException e) {
                        log.error("rss parsing Error {} : {}", f.getName(), f.getUrl());
                        return RssFeedResult.parsingError(f.getName());
                    }
                })
                .collect(Collectors.toList());
    }

}
