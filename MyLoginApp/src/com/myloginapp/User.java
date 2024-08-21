package com.myloginapp;

public class User {
    private String id;
    private String password;
    private String name;
    private String birthDate;
    private String gender;
    private String phoneNumber;
    private String role; // 추가된 부분

    public User(String id, String password, String name, String birthDate, String gender, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.role = "USER"; // 기본 역할은 일반 사용자
    }

    // getter와 setter 메서드

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
