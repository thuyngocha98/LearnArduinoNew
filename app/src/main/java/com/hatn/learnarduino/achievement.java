package com.hatn.learnarduino;

public class achievement {

    private String achivementID;
    private String Name;
    private String Description;
    private String img;
    private int exp;


    public achievement(){
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img){
        this.img=img;
    }

    public void setAchivementID(String achivementID) {
        this.achivementID=achivementID;
    }

    public String getAchivementID(){
        return achivementID;
    }

    public achievement(String achivementID, String name, String description, int exp, String img) {
        this.achivementID=achivementID;
        this.Name = name;
        this.Description = description;
        this.exp = exp;
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
