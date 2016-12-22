package com.epicodus.politicalactivismtracker.models;

import org.parceler.Parcel;

/**
 * Created by jensese on 12/21/16.
 */

@Parcel
public class Action {
    private String name;
    private String location;
    private String link;
    private String date;
    private String description;
    private String imageUrl;
    private String categoryCause;
    private String categoryAction;
    private String price;
    private String iDidThis;
    private String pushId;


    public Action() {
    }

    public Action(String name, String location, String link, String date, String description, String imageUrl, String categoryCause, String categoryAction, String price) {
        this.name = name;
        this.location = location;
        this.link = link;
        this.date = date;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryCause = categoryCause;
        this.categoryAction = categoryAction;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategoryCause() {
        return categoryCause;
    }

    public String getCategoryAction() {
        return categoryAction;
    }

    public String getPrice() {
        return price;
    }

    public String getIDidThis() {
        return iDidThis;
    }
    public String getPushId() {
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
