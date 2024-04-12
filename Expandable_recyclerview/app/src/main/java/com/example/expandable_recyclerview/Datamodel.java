package com.example.expandable_recyclerview;

import java.util.List;

public class Datamodel {

    private List<String> nestedList;
    private String itemText;
    private boolean isExpandable;


    public Datamodel(List<String> itemlist, String itemText) {
        this.nestedList = itemlist;
        this.itemText = itemText;
        isExpandable = false;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }


}
