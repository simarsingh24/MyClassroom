package com.svnit.harsimar.myclassroom;

public class faculty_data {
    private String profilePhoto,name,degree,desig,email,phone,research,areaOfInterest;

    public faculty_data(){

    }

    public faculty_data(String profilePhoto, String name, String degree, String desig,
                        String email, String phone, String research, String areaOfInterest) {
        this.profilePhoto = profilePhoto;
        this.name = name;
        this.degree = degree;
        this.desig = desig;
        this.email = email;
        this.phone = phone;
        this.research = research;
        this.areaOfInterest = areaOfInterest;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(String areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }
}
