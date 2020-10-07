package com.jyami.commitersewha.payload.ewhaScrap;

import lombok.*;
import org.jsoup.nodes.Document;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/10/07
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public final class NotificationResult {

    private List<NotificationEwha> jobList = Collections.emptyList();

    public static NotificationResult ewhaNotificationScraping(Document document, String baseUrl) {
        List<NotificationEwha> notificationEwhas = document.select("table tbody tr").stream()
                .filter(x -> x.select(".no img").isEmpty())
                .map(e -> NotificationEwha.of(e, baseUrl))
                .collect(Collectors.toList());
        return new NotificationResult(notificationEwhas);
    }
}
