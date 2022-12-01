package com.example.cinemahub_looknfeel;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieList {

    public static void main(String[] args) {

        //TODO This is a will pull the information we need from the webpage, however I put a temporary url in as we need that data from the user on which theatre he/she chooses
        try {
            Document doc = Jsoup.connect("https://www.imbd.com/list/ls055386927/").userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.79 Safari/537.36").get();
            Elements temp=doc.select("div.info");
            int i = 0;
            //TODO This part will loop over the list and grab the Titles of the movie being shown on the selected website, and with a little more code I will be able to grab the images and descriptions as well
            for (Element movieList:temp){
                i++;
                System.out.println(i+" "+movieList.getElementsByTag("a").first().text());
            }
        }
        catch(IOException e){
            //TODO Auto generated Catch block
            e.printStackTrace();
        }
    }
}
