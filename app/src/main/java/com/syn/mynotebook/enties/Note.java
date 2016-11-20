package com.syn.mynotebook.enties;

import java.util.Date;

/**
 * Created by 孙亚楠 on 2016/11/19.
 */

public class Note {
    private String title;
    private String content;
    private int id;
    private String date;
    public  Note(int id,String title,String content,String date){
        this.content=content;
        this.title=title;
        this.date=date;
        this.id=id;
    }
    public Note(int id ,String title,String date){
        this.date=date;
        this.title=title;
        this.id=id;
    }
    public  Note(String title,String content){
        this.title=title;
        this.content=content;
    }

    public Note(String title, String content, String date) {
        this.title=title;
        this.date=date;
        this.content=content;

    }

    public String getTitle(){
        return title;
            }
    public String getContent(){
        return content;
    }
    public int getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
}
