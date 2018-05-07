package com.example.eduardo.hotelcasamia;

public class Gallery {

    private String name;
    private String imageURL;

    /* Create the next constructor */

    public Gallery(String name, String ImageURL) {
        name = name;
        imageURL = ImageURL;
    }

    /* Getters and Setters */

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        name = Name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String ImageURL) {
        imageURL = ImageURL;
    }
}//End class
