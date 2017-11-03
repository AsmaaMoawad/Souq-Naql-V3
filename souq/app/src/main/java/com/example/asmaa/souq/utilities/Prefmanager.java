package com.example.asmaa.souq.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.asmaa.souq.Responses.LoginResponse;
import com.google.gson.Gson;

/**
 * Created by Asmaa on 17-Oct-17.
 */

public class Prefmanager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    String username="username";
    String password ="password";

    // shared pref mode
    int PRIVATE_MODE = 0;




    // Shared preferences file name
    private static final String USER_ID = "ID";
    private static final String LOGGED_IN = "logged_in";


    // Shared preferences file name
    private static final String PREF_NAME = "pref";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public Prefmanager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean check_login(){
        return  pref.getBoolean(LOGGED_IN,false);
    }

    public  void setLogin(){
        editor.putBoolean(LOGGED_IN,true);
        editor.commit();
    }

    public void logout() {
        editor.putBoolean(LOGGED_IN,false);
        editor.commit();
    }
    public  void  saveUserDataLogin(LoginResponse data){ // set the logged in user data
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("USERDATA", json);
        editor.commit();
    }


    public  LoginResponse  getCurrentUSer(){ // get the logged in user data
        Gson gson = new Gson();
        String json = pref.getString("USERDATA", "");
        return gson.fromJson(json, LoginResponse.class);
    }
}
