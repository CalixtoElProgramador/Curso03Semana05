package com.example.curso03semana03;

public class Pets {
    private int id;
    private String name;
    private String description;
    private int photo;
    private int likes;
    private int favorite;

    public Pets(int id, String name, String description, int photo, int likes, int favorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.likes = likes;
        this.favorite = favorite;

    }

    public Pets() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
