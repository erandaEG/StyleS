package com.example.earzo.styles.extra;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;

import com.example.earzo.styles.activities.activity_login;
import com.example.earzo.styles.activities.activity_navigationDrawer;

import javax.inject.Singleton;


public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    public static final String EXTRA_MY_EXCEPTION_HANDLER = "EXTRA_MY_EXCEPTION_HANDLER";
    private final Activity context;
    private final Thread.UncaughtExceptionHandler rootHandler;

    public MyExceptionHandler(Activity context) {
        this.context = context;

        rootHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override @TargetApi(21)
    public void uncaughtException(final Thread thread, final Throwable ex) {
        if (ex instanceof Exception) {
            UserSession userSession = UserSession.getUserSession(context);

            if(userSession.getLogInStatus()){

                ex.printStackTrace();
                Intent registerActivity = new Intent(context, activity_navigationDrawer.class);
                registerActivity.putExtra(EXTRA_MY_EXCEPTION_HANDLER, MyExceptionHandler.class.getName());
                registerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(registerActivity);
                android.os.Process.killProcess(android.os.Process.myPid());
            }else {

                userSession.logout();
                ex.printStackTrace();
                Intent registerActivity1 = new Intent(context, activity_login.class);
                registerActivity1.putExtra(EXTRA_MY_EXCEPTION_HANDLER, MyExceptionHandler.class.getName());
                registerActivity1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                registerActivity1.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

                context.startActivity(registerActivity1);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        } else {
            rootHandler.uncaughtException(thread, ex);
        }
    }
}
