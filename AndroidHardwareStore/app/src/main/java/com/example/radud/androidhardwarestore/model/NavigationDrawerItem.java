package com.example.radud.androidhardwarestore.model;

/**
 * Created by radud on 06/01/2016.
 */
public class NavigationDrawerItem {

    private String title;
    private int icon;

    public NavigationDrawerItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
