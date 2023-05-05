package com.example.EpicDoodles;

public class EmployeeModel {

    String ename,age,contact,position;

    EmployeeModel(){}

    public EmployeeModel(String ename, String age, String contact, String position) {
        this.ename = ename;
        this.age = age;
        this.contact = contact;
        this.position = position;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
