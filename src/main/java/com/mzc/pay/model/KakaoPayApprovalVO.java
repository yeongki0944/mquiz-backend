
package com.mzc.pay.model;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayApprovalVO {
        //response
        private String aid, tid, cid, sid;
        private String partner_order_id, partner_user_id, payment_method_type;
        private AmountVO amount; // json 객체를 받아오기 위함
        private CardVO card_info; // json 객체를 받아오기 위함
        private String item_name, item_code, payload;
        private Integer quantity, tax_free_amount, vat_amount;
        private Date created_at, approved_at;
    }

