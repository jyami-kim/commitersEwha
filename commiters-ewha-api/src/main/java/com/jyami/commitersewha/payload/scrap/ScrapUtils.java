package com.jyami.commitersewha.payload.scrap;

import com.jyami.commitersewha.exception.ScrapingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by jyami on 2020/10/07
 */
public final class ScrapUtils {

    private ScrapUtils() {
    }

    public static Document getCrawlingResult(String url) {
        try {
            return Jsoup.connect(url)
                    .timeout(4000)
                    .get();
        } catch (IOException e) {
            throw new ScrapingException("scraping 실패 : " + url);
        }
    }

}
