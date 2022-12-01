package com.mzc.pay.controller;

import com.mzc.pay.model.VueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/jq")
public class Jqurey {

    @Autowired
    VueDao vdao;

    @RequestMapping("jq.cls")
    public ModelAndView main(ModelAndView mv, HttpSession s, RedirectView rv){
        mv.setViewName("jq/test");
        return mv;
    }

    @RequestMapping("/pay.cls")
    public ModelAndView server(ModelAndView mv, HttpSession s, RedirectView rv){
        mv.setViewName("jq/serve");
        return mv;
    }

    @RequestMapping("/kakaopay.cls")
    public String kakaopay(){
        try {

            // 기본 정보
//            POST /v1/payment/ready HTTP/1.1
//            Host: kapi.kakao.com
//            Authorization: KakaoAK ${APP_ADMIN_KEY}
//            Content-type: application/x-www-form-urlencoded;charset=utf-8

            URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
            HttpURLConnection urlConnection = (HttpURLConnection) address.openConnection(); // 카카오 서버와 연결 하기 위함

            urlConnection.setRequestMethod("POST"); // 카카오서버에 요청방식
            urlConnection.setRequestProperty("Authorization","KakaoAK ${a4bb578bb956957c5938aba8347cdc42}");
            urlConnection.setRequestProperty("Content-type","application/x-www-form-urlencoded;charset=utf-8");
            urlConnection.setDoOutput(true); // doOupPut : 서버에 전달할 값이 있는지 없는지 설정하는 것 / 기본 설정이 false라서 true 라고 설정, doInPut 은 기본 값이 true

            String Parameter = "cid=TC0ONETIME" + // 테스트용 cid 설정
                    "&partner_order_id=partner_order_id" +
                    "&partner_user_id=partner_user_id" +
                    "&item_name=초코파이" +
                    "&quantity=1&total_amount=2200" +
                    "&vat_amount=200&tax_free_amount=0" +
                    "&approval_url=http://localhost/cls/jq/pay.cls" +
                    "&fail_url=http://localhost/fail" +
                    "&cancel_url=http://localhost/cancel";
            OutputStream sender = urlConnection.getOutputStream();
            DataOutputStream dataSender = new DataOutputStream(sender);
            dataSender.writeBytes(Parameter); // 파라미터값 형변환
            dataSender.close(); // outPutStream에 있는 flush() 함수가 자동으로 생성됨

            int result = urlConnection.getResponseCode();

            InputStream recipient;
            if(result == 200){
                recipient = urlConnection.getInputStream();
            }else{
                recipient = urlConnection.getErrorStream();
            }

            InputStreamReader reader = new InputStreamReader(recipient);
            BufferedReader bufferedReader = new BufferedReader(reader);
            return bufferedReader.readLine();

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "{\"result\":\"NO\"}";
    }

}
