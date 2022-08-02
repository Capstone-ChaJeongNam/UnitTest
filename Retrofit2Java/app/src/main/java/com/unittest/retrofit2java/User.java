package com.unittest.retrofit2java;
public class User {
    private String birth;
    private String email;
    private String uid;
    private String name;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "birth='" + birth + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}