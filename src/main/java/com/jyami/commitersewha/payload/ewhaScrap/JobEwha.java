package com.jyami.commitersewha.payload.ewhaScrap;

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
public class JobEwha {
    private int number;
    private String title;
    private String link;
    private boolean newBadge;

    public static JobEwha of(Element element, String baseUrl) {
        return JobEwha.builder()
                .number(Integer.parseInt(element.select(".no").text()))
                .title(element.select(".title").text())
                .link(baseUrl + element.select(".title a").attr("href"))
                .newBadge(!element.select(".title a img").isEmpty())
                .build();
    }
}
