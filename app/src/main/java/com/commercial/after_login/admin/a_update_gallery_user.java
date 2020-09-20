package com.commercial.after_login.admin;

public class a_update_gallery_user {
    String album_name,image;
    Long id;

    public a_update_gallery_user() {
    }

    public a_update_gallery_user(String album_name, String image) {
        this.album_name = album_name;
        this.image = image;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}