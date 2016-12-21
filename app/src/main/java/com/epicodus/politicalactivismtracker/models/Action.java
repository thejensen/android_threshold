package com.epicodus.politicalactivismtracker;

/**
 * Created by jensese on 12/21/16.
 */

public class Action {
    private String mName;
    private String mLocation;
    private String mLink;
    private String mDate;
    private String mDescription;
    private int mResourceIds;
    private String mCategoryCause;
    private String mCategoryAction;
    private int mPrice;
    private String mIDidThis;

    public Action(String mName, String mLocation, String mLink, String mDate, String mDescription, int mResourceIds, String mCategoryCause, String mCategoryAction, int mPrice, String mIDidThis, int mImpactAssessment) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mLink = mLink;
        this.mDate = mDate;
        this.mDescription = mDescription;
        this.mResourceIds = mResourceIds;
        this.mCategoryCause = mCategoryCause;
        this.mCategoryAction = mCategoryAction;
        this.mPrice = mPrice;
    }

    public String getName() {
        return mName;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getLink() {
        return mLink;
    }

    public String getDate() {
        return mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getResourceIds() {
        return mResourceIds;
    }

    public String getCategoryCause() {
        return mCategoryCause;
    }

    public String getCategoryAction() {
        return mCategoryAction;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getIDidThis() {
        return mIDidThis;
    }
}
