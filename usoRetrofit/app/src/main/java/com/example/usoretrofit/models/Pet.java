package com.example.usoretrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pet {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("created_at")
    @Expose
    private String created_at;


    public Pet(String name, String age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Pet(String id, String name, String age, String color) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public  String toString(){
        return "Pet{" +
                ", name='"+ name + '\'' +
                ", age='"+ age + '\'' +
                ", color='"+ color + '\''+
                '}';
    }
}
