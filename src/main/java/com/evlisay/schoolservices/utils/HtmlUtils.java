package com.evlisay.schoolservices.utils;


import org.jsoup.Jsoup;


public class HtmlUtils {
    //个人信息
    public static String getPersonUrl(String html){
        return  Jsoup.parse(html).select("li.top")
                .get(3)
                .select("ul.sub")
                .select("li")
                .select("a")
                .attr("href");
    }
    //成绩表
    public static String getResultUrl(String html){
        return Jsoup.parse(html)
                .select("ul.sub")
                .get(4)
                .select("li")
                .get(3)
                .select("a")
                .attr("href");
    }
    //课程表
    public static String getClassPerson(String html){
        return Jsoup.parse(html).select("li.top")
                .get(4)
                .select("ul.sub")
                .get(0)
                .select("li")
                .select("a")
                .attr("href");
    }

    public static String getNumbers(String html){
        return Jsoup.parse(html).select("form")
                .attr("action")
                .substring(16, 27);
    }

    public static String getPassWordChanger(String html){
        return Jsoup.parse(html).select("ul.sub")
                .get(3)
                .select("li")
                .get(1)
                .select("a")
                .attr("href");
    }

    public static String getName(String html){
        return Jsoup.parse(html)
                .select("span")
                .text()
                .substring(0, 5);
    }

    public static String getPersonPhoto(String html){
        return Jsoup.parse(html).select("img").attr("src");
    }
}
