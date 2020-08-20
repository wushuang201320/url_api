package com.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Url implements Serializable {
    private static final long serialVersionUID = -3898260210892737842L;
    private Integer id;
    private String shortUrl;
    private String longUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
