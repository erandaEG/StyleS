package com.example.earzo.styles.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.BuildConfig;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.example.earzo.styles.extra.MyExceptionHandler;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.InitializationCallback;

public abstract class activity_base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();

        Fabric.with(new Fabric.Builder(this).

                kits(new Crashlytics.Builder().

                        core(core).

                        build()).

                initializationCallback(new InitializationCallback<Fabric>() {
                    @Override
                    public void success (Fabric fabric){
                        new MyExceptionHandler(activity_base.this);
                    }

                    @Override
                    public void failure (Exception e){

                    }
                }).

                build());
    }
}