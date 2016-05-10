package com.beepbeat.HTMLLib;


public class Attribute {

    private String name, value;

    public Attribute(String name, String value){
        this.name = name;
        if (value.contains("\""))
            this.value = value;
        else
            this.value = "\"" + value + "\"";
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return name + "=" + value;
    }
}
