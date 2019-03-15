package com.hatn.learnarduino;

public class achievement {

    private String name;
    private String description;
    private int exp;

    public achievement(String name, String description, int exp) {
        this.name = name;
        this.description = description;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
