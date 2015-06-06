package com.ohgo.ohgo.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Ruben on 6/5/15.
 */

@ParseClassName("Employee")
public class Employee extends ParseObject {

    public Employee() {}

    public String getName() {return getString("name"); }
    public void setName(String title) {put("name", title);}

    public ParseUser getAuthor() {return getParseUser("author");}
    public void setAuthor(ParseUser user) {
        put("author", user);
    }

    public String getPassword() {
        return getString("password");
    }
    public void setPassword(String value) {
        put("password", value);
    }

    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }
    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }

    public String getBirth() {
        return getString("birth");
    }
    public void setBirth(String value) {
        put("birth", value);
    }

    public String getPhone(){return getString(("phone"));}
    public void setPhone(String value){put("phone",value);}

    public String getEmail(){return getString("email"); }
    public void setEmail(String value){put("email",value);}
}
