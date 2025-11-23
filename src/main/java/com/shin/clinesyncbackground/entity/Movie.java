package com.shin.clinesyncbackground.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movie {
    private Long id;
    private String name;
    private BigDecimal rating;
    private byte[] image; // 存储图片的字节数组
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Constructors
    public Movie() {}

    public Movie(String name, BigDecimal rating, byte[] image) {
        this.name = name;
        this.rating = rating;
        this.image = image;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}