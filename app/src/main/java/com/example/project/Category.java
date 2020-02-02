package com.example.project;

public class Category {
    String name;
    int imgno;
    final static int imglist[] = {
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.duck,
            R.drawable.fish,
            R.drawable.horse,
            R.drawable.parrot,
            R.drawable.pigeon,
            R.drawable.rabbit
    };

    public Category(String name, int imgno){
        this.name = name;
        this.imgno = imgno;
    }

    public String getName(){
        return name;
    }

    public void setImgno(int imgno){
        this.imgno = imgno;
    }

    public int getImgno(){
        return imgno;
    }

    public void setName(String name){
        this.name = name;
    }
}
