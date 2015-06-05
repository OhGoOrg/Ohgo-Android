package com.ohgo.ohgo.models;

import java.io.Serializable;

/**
 * Created by Rick on 04/06/15.
 */
public class User implements Serializable {

    private String name;
    private String urlPicture;

    public User(String name, String urlPicture) {
        this.name = name;
        this.urlPicture = urlPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
