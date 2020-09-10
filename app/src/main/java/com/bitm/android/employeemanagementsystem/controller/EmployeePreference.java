package com.bitm.android.employeemanagementsystem.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class EmployeePreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String IS_LOGGED_IN = "isLoggedIn";

    public EmployeePreference(Context context) {
        sharedPreferences = context.getSharedPreferences("emp_prefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoginStatus(boolean status) {
        editor.putBoolean(IS_LOGGED_IN, status);
        editor.commit();//save
    }

    public boolean getLoginStatus() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
