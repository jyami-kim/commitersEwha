package com.jyami.commitersewha.payload.response;

import com.jyami.commitersewha.payload.rssFeed.RssFeedContents;
import com.jyami.commitersewha.payload.rssFeed.RssFeedInfo;
import lombok.*;

import java.util.List;

/**
 * Created by jyami on 2020/11/21
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class RssFeedResponse {
    private List<RssFeedInfo> rssFeedInfos;
    private List<RssFeedContents> rssFeedContents;
}
