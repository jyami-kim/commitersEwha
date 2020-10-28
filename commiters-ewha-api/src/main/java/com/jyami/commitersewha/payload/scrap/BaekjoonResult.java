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
public class BaekjoonResult {
    private int rank;
    private String universityName;
    private String link;
    private int totalNumber;
    private int collectNumber;
    private int submitNumber;
    private String rate;

    public static BaekjoonResult of(Element element, String baseUrl) {
        return BaekjoonResult.builder()
                .rank(Integer.parseInt(element.select("td:nth-child(1)").text()))
                .universityName(element.select("td:nth-child(2)").text())
                .link(baseUrl + element.select("td:nth-child(2) a").attr("href"))
                .totalNumber(Integer.parseInt(element.select("td:nth-child(4)").text()))
                .collectNumber(Integer.parseInt(element.select("td:nth-child(5)").text()))
                .submitNumber(Integer.parseInt(element.select("td:nth-child(6)").text()))
                .rate(element.select("td:nth-child(7)").text())
                .build();
    }
}
