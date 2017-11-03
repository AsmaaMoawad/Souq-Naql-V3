package com.example.asmaa.souq.Responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asmaa on 17-Oct-17.
 */

public class LoginResponse{


    public String getToken() {
        return token;
    }

    @SerializedName("token")
    public String token;
}
