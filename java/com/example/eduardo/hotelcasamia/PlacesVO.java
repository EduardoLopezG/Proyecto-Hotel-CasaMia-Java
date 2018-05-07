package com.example.eduardo.hotelcasamia;


import java.io.Serializable;

public class PlacesVO implements Serializable{

    private String name;
    private String info;
    private String description;
    private int foto;
    private int imageDetail;

    /* Empty Constructor */
    public PlacesVO() {
    }

    /* Constructor */
    public PlacesVO(String name, String info, String description,int foto, int imageDetail) {
        this.name = name;
        this.info = info;
        this.description = description;
        this.foto = foto;
        this.imageDetail = imageDetail;
    }

    /* Getters & Setters */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(int imageDetail) {
        this.imageDetail = imageDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}//End class
