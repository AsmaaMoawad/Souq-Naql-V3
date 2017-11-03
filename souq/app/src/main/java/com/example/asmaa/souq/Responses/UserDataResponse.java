package com.example.asmaa.souq.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 24-Oct-17.
 */

public class UserDataResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("Name")
    public String Name;
    @SerializedName("Name_Company")
    public String Name_Company;
    @SerializedName("email")
    public String email;
    @SerializedName("PersonalImage")
    public String PersonalImage;
    @SerializedName("phone_number")
    public String phone_number;
    @SerializedName("Age")
    public int Age;
    @SerializedName("Address")
    public String Address;
    @SerializedName("Governorate")
    public String Governorate;
    @SerializedName("licenceNo")
    public String licenceNo;
    @SerializedName("Activity")
    public String Activity;
    @SerializedName("notes")
    public String notes;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;
}
