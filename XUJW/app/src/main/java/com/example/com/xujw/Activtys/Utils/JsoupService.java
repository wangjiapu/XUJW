package com.example.com.xujw.Activtys.Utils;

import android.util.Log;

import com.example.com.xujw.Activtys.Beans.Assist;
import com.example.com.xujw.Activtys.Beans.CourseInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 蒲家旺 on 2017/2/6.
 *
 * 解析html数据
 */
public class JsoupService {
    public static String myname;
    /**
     * 存储课程表的解析文件
     */

    public static Map<String,String> kechengbiao;

    /**
     * 存储菜单文件
     */
    public static Map<String, String> linkMap;

    /**
     * 成绩
     * 必修成绩，选修成绩
     */
    public static List<CourseInfo> chengjidan;

    public static List<CourseInfo> chengjidan2;

    /**
     * 培养计划
     */
    public static Map<String,String> plan;


    /**
     * 个人信息
     */
    public static Map<String,String>  person;

    private  static boolean isfirst1=true,isfirst2=true;

    /**
     * 判断是否登录成功
     *
     * @param content 网页html源码
     * @return 若返回值为null，则表示登陆失败。
     */


    public static String isLogin(String content){

        Document document= Jsoup.parse(content,"gb2312");
        Elements elements=document.select("span#xhxm");

        try {
            Element element = elements.get(0);
            myname=element.text().substring(0,element.text().length()-2);
            return element.text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析menu 获得子菜单的url
     */

    public static Map<String,String> parseMenu(String content){

        Map<String,String> map=new HashMap<>();
        Document document=Jsoup.parse(content,"gb2312");
        Elements elements=document.select("ul.nav a[target=zhuti]");
        for(Element element:elements){
            String str= Assist.encodeUrl(element.attr("href"));
            Log.e("element",str);
            map.put(element.text(),str);
        }
        linkMap=map;
        return map;
    }

    /**
     * 解析出来的课程表信息
     * @param content
     *
     */

    public static void parseCourse(String content){

       // String [][]course=new String [4][6];

        Map<String,String> kbmp=new LinkedHashMap<>();
        Document document=Jsoup.parse(content);//其实主要的就是一个Document的获取
        //还有就是 Elements的获取，主要的是select中选取文件的设置
        Elements elements=document.select("table#Table1.blacktab tr");

        //移除多余的信息
        elements.remove(0);
        elements.remove(0);
        elements.remove(1);
        elements.remove(2);
        elements.remove(3);
        //elements.remove(4);
        //elements.remove(4);
        //elements.remove(4);
       // elements.remove(4);


        for(int i=0;i<elements.size();i++){
            Elements elements1=elements.get(i).select("td");
            if (i==0||i==2){
                elements1.remove(0);
            }

            elements1.remove(0);
           // elements1.remove(5);
           // elements1.remove(5);


            for (int j=0;j<elements1.size();j++){
                String[] strings;
                String string=elements1.get(j).text().replace("<br>","\n").replace("&nbsp;","");

                if (!string.equals("")){

                    strings=string.split(" ");
                    if (strings.length>3){
                        if (strings[0].length()>12){
                            strings[0]=strings[0].substring(0,12);
                        }
                        string= strings[0]+"\n"+strings[2]+"\n"+strings[3];
                    }
                }
                kbmp.put(i+"*"+j,string);

            }
        }
        kechengbiao=kbmp;

    }

    /**
     *  解析成绩单
     * @param content
     */
    public static List<CourseInfo> parseSore(String content){
        List<CourseInfo> chengji=new ArrayList<>();
        Document document=Jsoup.parse(content);
        Elements elements=document.select("table#Datagrid1.datelist tr");
        elements.remove(0);
        for (Element element:elements){
            Elements elements1=element.select("td");

            CourseInfo courseInfo=new CourseInfo();
            courseInfo.setCourse_name(elements1.get(3).text());
            //Log.e("课程名称",elements1.get(3).text());
            courseInfo.setCourse_nature(elements1.get(4).text());
            //Log.e("性质",elements1.get(4).text());
            courseInfo.setCourse_belong(elements1.get(5).text().replace("&nbsp;",""));
            courseInfo.setCourse_credit(elements1.get(6).text());
            courseInfo.setCourse_average_point(elements1.get(7).text());
            courseInfo.setCourse_score(elements1.get(8).text());
            //Log.e("成绩",elements1.get(8).text());
            courseInfo.setCourse_make_up_score(elements1.get(10).text().replace("&nbsp;",""));
            courseInfo.setCourse_college(elements1.get(12).text().replace("&nbsp;",""));
            chengji.add(courseInfo);
        }
        return chengji;
    }
    public static void parseSoreA(String content){
        if (isfirst1){
            chengjidan=parseSore(content);
            isfirst1=false;
        }
        else{
            chengjidan.clear();
            chengjidan.addAll(parseSore(content));
        }

    }
    public static void parseSoreB(String content){
        if (isfirst2){
            chengjidan2=parseSore(content);
            isfirst2=false;
        }
        else{
            chengjidan2.clear();
            chengjidan2.addAll(parseSore(content));
        }
    }


    public static Map<String, Object> getScoreYear(String content){

        Map<String, Object> map = new HashMap<>();
        Document document = Jsoup.parse(content);
        Elements elements = document.select("input[name=__VIEWSTATE]");
        map.put("__VIEWSTATE", elements.get(0).val());
        elements = document.select("input[name= __EVENTARGUMENT]");
        map.put("__VIEWSTATEGENERATOR", elements.get(0).val());
        elements = document.select("select[name=ddlXN] option");
        List<String> score_year = new ArrayList<>();
        for (Element element : elements) {
            score_year.add(element.val());
        }
        map.put("score_year", score_year);
        elements = document.select("select[name=ddlXQ] option");
        List<String> score_semester = new ArrayList<>();
        for (Element element : elements) {
            score_semester.add(element.val());
        }
        map.put("score_semester", score_semester);

        return map;
    }

    /**
     * 解析个人信息
     * @param content
     */

    public static void parseperson(String content){
        Document document = Jsoup.parse(content);
        Map<String, String> map = new HashMap<>();
        Elements elements1 = document.select("table.formlist span");
        map.put(elements1.get(0).text(), elements1.get(1).text());
        map.put(elements1.get(7).text(), elements1.get(8).text());
        map.put(elements1.get(19).text(), elements1.get(20).text());
        map.put(elements1.get(44).text(), elements1.get(45).text());
        map.put(elements1.get(56).text(), elements1.get(57).text());
        map.put(elements1.get(74).text(), elements1.get(75).text());
        map.put(elements1.get(86).text(), elements1.get(87).text());
        map.put(elements1.get(96).text(), elements1.get(97).text());
        map.put(elements1.get(100).text(), elements1.get(101).text());
        map.put(elements1.get(112).text(), elements1.get(113).text());
        map.put(elements1.get(117).text(), elements1.get(118).text());

        person=map;
    }

}
