

package com.msewa.madmovegame.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserInfo {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("contactNo")
    @Expose
    private String contactNo;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("sessionId")
    @Expose
    private String sessionId;


    public UserInfo() {

    }

    public UserInfo(String firstName, String lastName, String email, String gender, String contactNo, String password, String sessionId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.contactNo = contactNo;
        this.password = password;
        this.sessionId = sessionId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", password='" + password + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}