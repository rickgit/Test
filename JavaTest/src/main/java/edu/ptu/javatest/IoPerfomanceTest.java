package edu.ptu.javatest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class IoPerfomanceTest {
    @Test
    public void test001ObjectIO() {//str 64ms //json 167ms
        ArrayList<String> strings = initObject();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./object"));
            oos.writeObject(strings);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test

    public void test002ReadObj() {//str 160ms //json 518ms
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./object"));
            Object o = ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test003BufferIo() {//str 30ms
        ArrayList<String> strings = initObject();
        try {
            PrintWriter oos = new PrintWriter(new OutputStreamWriter(new FileOutputStream("./object")));
            for (int i = 0; i < strings.size(); i++) {
                oos.println(strings.get(i));
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test004ReadBuffered() {//str 14ms
        try {
            ArrayList<String> newStr = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./object")));
            String str = null;
            while ((str = br.readLine()) != null) {
                newStr.add(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //200条数据以下，序列化与反序列化快，json 序列化字符串toJson慢
    ArrayList<Article> articles = initArticleList();
    @Test
    public void test011ObjectIO() {//1000 json 167ms, 15条 json 11ms
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./object"));
            oos.writeObject(articles);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test012ReadObj() {//1000json 518ms ,15条 json 18ms
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./object"));
            Object o = ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test013WriteJson() throws Exception {//1000json 277ms,15条 json 211ms
        String string = new Gson().toJson(articles, ArrayList.class);
        BufferedWriter oos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./object")));
        oos.write(string);
        oos.close();
    }

    @Test
    public void test014ReadJson() throws Exception {//1000json 171ms,15条 json 9ms
        ArrayList<Article> articles = initArticleList();

        BufferedReader oos = new BufferedReader(new InputStreamReader(new FileInputStream("./object")));
        ArrayList string = new Gson().fromJson(oos, ArrayList.class);
        oos.close();
    }

    public ArrayList<String> initObject() {
        ArrayList<String> objects = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            objects.add("string: " + i);
        }
        return objects;
    }

    public ArrayList<Article> initArticleList() {
        ArrayList<Article> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new Article());
        }
        return objects;
    }
    //Article{recoid='4195442930308615977', id='14441935368216035340', title='过去韩国人赚中国玩家钱, 现在腾讯狂赚韩国人钱, 一家就赚回1100亿!',
    // itemType=1, styleType=5, grabTime=0, publishTime=1564371541000, url='https://m.uczzd.cn/webview/news?app=webbrowser-iflow&aid=14441935368216035340&cid=100&zzd_from=webbrowser-iflow&uc_param_str=dndsfrvesvntnwpfgibicp&recoid=4195442930308615977&rd_type=reco&sp_gz=0&activity=1&activity2=1&dn=fd1da7d2df5f3aca0adf23047a5d2d54&enuid=AAMx76bSOHuDBpky2EsEH4%2BwTlv4cf6GUVUTa%2Fh1pbyqtw%3D%3D&uc_iflow_biz=cmt%3A1&imei=bTkwAADz55j%2BtHwy8ZAXth2JV%2Ft3Jim9',
    // sourceName='17173游戏', originSourceName='17173游戏', summary='', tags=[], category=[], content='', contentLength=0, thumbnails=[
    // Thumbnail [url=http://image.uczzd.cn/18390492168200006757.jpg?id=0&from=export, width=480, height=270, type=],
    // Thumbnail [url=http://image.uczzd.cn/13003995450671830833.jpg?id=0&from=export, width=480, height=270, type=],
    // Thumbnail [url=http://image.uczzd.cn/13035065252692255576.jpg?id=0&from=export, width=480, height=270, type=]
    // ],
    // bottomleftmark=BottomLeftMark [mark=, markColor=0, markIconUrl=],
    // images=[], commentCnt=80, videos=[], shareUrl='', cmtUrl='', showImpressionUrl='', likeCnt=27, supportCnt=0, opposeCnt=0,
    // adContent={industry1=, ad_type=, scheme=, action_type=, industry2=, dmp_id=, search_id=, ad_id=, ad_source=, industry1_description=, industry2_description=, ad_source_description=, ad_is_effect=},
    // ad_content_click_ad_url_array=null, ad_content_show_ad_url_array=null, fl_article_content_click_url_array=null, BottomLeftMark={}, openType=0
    // }

    //NotSerializableException
    public static class Article implements Serializable {
        private static final long serialVersionUID =1l;
        private String recoid;
        private String id;
        private String title;
        private int itemType;
        private int styleType;
        private int grabTime;
        private long publishTime;
        private String url;
        private String sourceName;
        private String originSourceName;
        private String summary;
        private ArrayList<String> tags;
        private ArrayList<String> category;
        private String content;
        private int contentLength;
        private ArrayList<Thumbnail> thumbnails;
        private HashMap<String,BottomLeftMark> bottomleftmarkMap;
        private ArrayList<Thumbnail> images;
        private int commentCnt;
        private ArrayList<String> videos;
        private String shareUrl;
        private String cmtUrl;
        private String showImpressionUrl;
        private int likeCnt;
        private int supportCnt;
        private int opposeCnt;
        private HashMap<String,Thumbnail> adContent;
        private String ad_content_click_ad_url_array;
        private String ad_content_show_ad_url_array;
        private String fl_article_content_click_url_array;
        private BottomLeftMark bottomLeftMark;
        private int openType;
    }
    public static class Thumbnail{
        private String url;
        private int width;
        private int height;
        private int type;

    }
    public static class BottomLeftMark{
        private String mark;
        private int markColor;
        private String markIconUrl;
    }

}
