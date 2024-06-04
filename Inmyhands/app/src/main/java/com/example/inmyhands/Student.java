package com.example.inmyhands;

public class Student {
    private String name;
    private String branch;
    private String registerno;
    private String vuid;

    public Student() {
    }

    public Student(String name, String branch, String registerno, String vuid) {
        this.name = name;
        this.branch = branch;
        this.registerno = registerno;
        this.vuid = vuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRegisterno() {
        return registerno;
    }

    public void setRegisterno(String registerno) {
        this.registerno = registerno;
    }

    public String getVuid() {
        return vuid;
    }

    public void setVuid(String vuid) {
        this.vuid = vuid;
    }
}

