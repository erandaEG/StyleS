package com.example.earzo.styles.extra;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

public class UserSession {
    private static SharedPreferences sp;
    private static UserSession userSession = new UserSession();

    private UserSession() {
    }

    public static UserSession getUserSession(Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        return userSession;
    }

    public void setLogInStatus(boolean loggedIn){
        sp.edit().putBoolean("Login status", loggedIn).apply();
    }

    public boolean getLogInStatus(){
        return sp.getBoolean("Login status", false);
    }

    public void setUID(Long userID){
        sp.edit().putLong("userID", userID).apply();
    }

    public Long getUID(){
        return sp.getLong("userID", 0);
    }

    public void logout(){
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("userID");
        editor.remove("Login status");
        editor.clear();
        editor.apply();
    }
}
