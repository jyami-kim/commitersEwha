package com.jyami.commitersewha.payload.rssFeed;

import com.jyami.commitersewha.exception.FeedParsingException;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jyami on 2020/10/08
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RssFeedParser {

    public static SyndFeed parsingEntry(String url) {
        try {
            return new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException | IOException e) {
            throw new FeedParsingException(e.getMessage());
        }
    }

}
