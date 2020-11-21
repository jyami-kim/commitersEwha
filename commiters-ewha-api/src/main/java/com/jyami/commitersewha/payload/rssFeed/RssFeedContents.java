package com.jyami.commitersewha.payload.rssFeed;

import com.jyami.commitersewha.domain.rssFeed.FeedType;
import com.jyami.commitersewha.domain.rssFeed.RssFeed;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import lombok.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jyami on 2020/10/08
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class RssFeedContents implements Comparable<RssFeedContents> {

    private static final int LIMIT_TEXT = 500;

    private String company;
    private String title;
    private String link;
    private String date;
    private String description;
    private String color;
    private FeedType feedType;
    private String image;

    public static RssFeedContents of(RssFeed rssFeed, SyndEntry entry) {
        String description = disposeDescription(entry);
        return RssFeedContents.builder()
                .company(rssFeed.getName())
                .color(rssFeed.getColor())
                .feedType(rssFeed.getFeedType())
                .title(entry.getTitle())
                .link(disposeLink(rssFeed.getName(), entry))
                .date(disPoseDate(entry))
                .description(parsingText(description))
                .image(disposeImage(description))
                .build();
    }

    private static String disPoseDate(SyndEntry entry) {
        Date date = entry.getPublishedDate() != null ? entry.getPublishedDate() : entry.getUpdatedDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        return format.format(date);
    }

    private static String disposeImage(String html){
        Document parse = Jsoup.parse(html);
        Element element = parse.selectFirst("img");
        if(element != null){
            return element.attr("src");
        }
        return null;
    }

    private static String disposeLink(String company, SyndEntry entry) {
        if (company.equals("Amazon Developer")) {
            return "https://developer.amazon.com" + entry.getLink();
        }
        return entry.getLink();
    }

    private static String disposeDescription(SyndEntry entry) {
        SyndContent descriptionEntry = entry.getDescription();
        String description = "";
        if (descriptionEntry != null) {
            description = descriptionEntry.getValue();
        } else if (entry.getContents().size() != 0) {
            description = entry.getContents().get(0).getValue();
        }
        return description;
    }

    protected static String parsingText(String html) {
        String description = Jsoup.parse(html).text();
        if (description.length() > LIMIT_TEXT) {
            return description.substring(0, LIMIT_TEXT);
        }
        return description;
    }

    @Override
    public int compareTo(RssFeedContents o) {
        return o.getDate().compareTo(date);
    }
}
