
package com.mzc.pay.model;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayReadyVo {
    private String tid, next_redirect_pc_url;
    private Date created_at;
}

