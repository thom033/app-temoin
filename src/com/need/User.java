package com.need;
public class User {
    String name;
    String address;
    int age;
    String option;

    public User() {

    }

    public User(String name, String address, int age, String option) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
