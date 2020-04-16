package ca.bcit.android.finalproject;

import com.google.firebase.firestore.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Movie {

    private String name;


    private String description;
    private String url;

    public Movie(){

    }

    public Movie(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
