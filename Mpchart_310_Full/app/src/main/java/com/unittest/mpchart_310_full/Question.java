package com.unittest.mpchart_310_full;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String category;
    private String title;
    private Boolean isExpanded = false;
    private List<Consequence> consequences;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Question(String title, List<Consequence> consequences) {
        this.title = title;
        this.consequences = consequences;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }

//    public boolean isExpanded() {
//        return isExpanded;
//    }


    public List<Consequence> getConsequences() {
        return consequences;
    }

    public void setConsequences(List<Consequence> consequences) {
        this.consequences = consequences;
    }



}
