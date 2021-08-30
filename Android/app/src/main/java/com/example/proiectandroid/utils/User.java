package com.example.proiectandroid.utils;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="userId")
    private long userId;
    @ColumnInfo(name="username")
    private String username;
    @ColumnInfo(name="password")
    private String password;
    @ColumnInfo(name="weight")
    private float weight;
    @ColumnInfo(name="height")
    private float height;
    @ColumnInfo(name="age")
    private int age;
    @ColumnInfo(name="sex")
    private String sex;
    @ColumnInfo(name="personType")
    private String personType;

    @Ignore
    public User(){

    }

    @Ignore
    public User(String username, String password, float weight, float height, int age, String sex, String personType) {

        this.username = username;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.personType = personType;
    }


    public User(long userId, String username, String password, float weight, float height, int age, String sex, String personType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.personType = personType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public double calculateBMI(float weight, float height){
        weight = getWeight();
        height = getHeight()/100;
        return weight / (height * height);
    }
}
