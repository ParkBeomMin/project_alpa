package com.example.park.management;

/**
 * Created by jisung on 2017. 2. 20..
 */

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlParsing {

    public void parsing(String str) throws Exception {
        Document document = Jsoup.connect("http://jisung0920.cafe24.com/").get();
        document.getElementById("recent-post-2");
        Log.d("String","Test");
        if (null != document) {
            // id가 realrank 인 ol 태그 아래 id가 lastrank인 li 태그를 제외한 모든 li 안에 존재하는
            // a 태그의 내용을 가져옵니다.
            Elements elements = document.select("div#entry-content");
            for(Element e :elements){

                Log.d("parsing :",e.text()+"asdf");
            }
/*
            for (int i = 0; i < elements.size(); i++) {
                System.out.println("------------------------------------------");
                System.out.println("검색어 : " + elements.get(i).attr("title"));
                System.out.println("랭킹 : " + (i + 1));
                System.out.println("상승여부 : " + elements.get(i).select("span.tx").text());
                System.out.println("상승단계 : " + elements.get(i).select("span.rk").text());
                System.out.println("링크 URL : " + elements.get(i).attr("href"));
                System.out.println("------------------------------------------");
            }
 */      }
    }
}


