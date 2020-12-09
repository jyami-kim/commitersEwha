package com.jyami.commitersewha.payload.scrap;

import lombok.*;
import org.jsoup.nodes.Element;

/**
 * Created by jyami on 2020/10/07
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class NotificationEwha {
    private long number;
    private String title;
    private String type;
    private String date;
    private String link;
    private boolean newBadge;

    public static NotificationEwha of(Element element, String baseUrl) {
        return NotificationEwha.builder()
                .number(Integer.parseInt(element.select(".no").text()))
                .type(element.select("td:nth-child(2)").text())
                .date(element.select("td:nth-child(5)").text())
                .title(element.select(".title").text())
                .link(baseUrl + element.select(".title a").attr("href"))
                .newBadge(!element.select(".title a img").isEmpty())
                .build();
    }
}
