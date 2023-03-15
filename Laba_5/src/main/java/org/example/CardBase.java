package org.example;

abstract public class CardBase {
    protected int id;
    protected String name;
    protected String publishingHouse;
    protected int year;
    protected String comment;
    protected String city;
    public CardBase(String name, int year, int id, String publishingHouse, String city){
        this.name = name;
        this.year = year;
        this.id = id;
        this.publishingHouse = publishingHouse;
        this.city = city;
    }
    public String getName(){
        return name;
    }
    public int getYear(){
        return year;
    }
    public int getId(){
        return id;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return comment;
    }
    public String getPublishingHouse(){
        return publishingHouse;
    }
    abstract public String getInfo();
}