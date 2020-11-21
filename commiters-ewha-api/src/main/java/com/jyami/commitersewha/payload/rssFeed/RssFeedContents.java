package com.jyami.commitersewha.payload.rssFeed;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import lombok.*;
import org.jsoup.Jsoup;

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
    private Date date;
    private String description;

    public static RssFeedContents of(String company, SyndEntry entry) {

        return RssFeedContents.builder()
                .company(company)
                .title(entry.getTitle())
                .link(disPoseLink(company, entry))
                .date(disPoseDate(entry))
                .description(parsingAndLimitText(disPoseDescription(entry)))
                .build();
    }

    private static Date disPoseDate(SyndEntry entry) {
        return entry.getPublishedDate() != null ? entry.getPublishedDate() : entry.getUpdatedDate();
    }

    private static String disPoseLink(String company, SyndEntry entry) {
        if (company.equals("Amazon")) {
            return "https://developer.amazon.com/blogs/" + entry.getLink();
        }
        return entry.getLink();
    }

    private static String disPoseDescription(SyndEntry entry) {
        SyndContent descriptionEntry = entry.getDescription();
        String description = "";
        if (descriptionEntry != null) {
            description = descriptionEntry.getValue();
        } else if (entry.getContents().size() != 0) {
            description = entry.getContents().get(0).getValue();
        }
        return description;
    }

    protected static String parsingAndLimitText(String html) {
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
