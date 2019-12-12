package com.programrabbit.adsmart;

import android.app.Application;

import com.programrabbit.adsmart.Network.AdManager;
import com.programrabbit.adsmart.Network.UserData;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApp extends Application {

    UserData userData = UserData.getInstance();
    AdManager adManager = AdManager.getInstance();

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/MYRIADPRO-REGULAR.OTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
