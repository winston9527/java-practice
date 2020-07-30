package com.winston.practice.jdk.generic;


public class Male extends Person {

    private String wifeName;

    public Male() {
    }

    public Male(String name, int age, String wifeName) {
        super(name, age);
        this.wifeName = wifeName;
    }

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    @Override
    public String toString() {
        return "Male [wifeName=" + wifeName + "]";
    }


}
