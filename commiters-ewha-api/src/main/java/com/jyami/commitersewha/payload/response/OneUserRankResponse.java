package com.jyami.commitersewha.payload.response;

import lombok.*;

/**
 * Created by jyami on 2020/11/30
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class OneUserRankResponse {
    private UserRankInfoResponse weekRank;
    private UserRankInfoResponse quarterRank;
}
