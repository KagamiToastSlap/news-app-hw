package com.example.rkjc.news_app_2;

public class NewsItem {
    private String aurthor;
    private String title;
    private String news_Descriptions;
    private String theurl;
    private String urlimage;
    private String published;

    public NewsItem (String aurthor, String title, String news_Descriptions, String theurl, String urlimage, String published)
    {
        this.aurthor = aurthor;
        this.title = title;
        this.news_Descriptions = news_Descriptions;
        this.theurl = theurl;
        this.urlimage = urlimage;
        this.published = published;

    }

    public String get_Aurthor()
    {
        return aurthor;
    }

    public String get_Title()
    {
        return title;
    }

    public String get_news_Descriptions()
    {
        return news_Descriptions;
    }

    public String get_theurl()
    {
        return theurl;
    }

    public String get_urlimage()
    {
        return urlimage;
    }

    public String get_published()
    {
        return published;
    }



}
