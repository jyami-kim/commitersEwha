package com.jyami.commitersewha.payload.ewhaScrap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public final class JobResult {

    private List<JobEwha> jobList = Collections.emptyList();

    public static JobResult ewhaJobScraping(Document document, String baseUrl) {
        List<JobEwha> jobEwhas = document.select("table tbody tr").stream()
                .map(e -> JobEwha.of(e, baseUrl))
                .collect(Collectors.toList());
        return new JobResult(jobEwhas);
    }
}
