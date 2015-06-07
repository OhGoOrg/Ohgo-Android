package com.ohgo.ohgo.app;

import android.app.Application;
import android.util.Log;

import com.ohgo.ohgo.activities.MainActivity;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.SaveCallback;


/**
 * Created by Ruben on 6/4/15.
 */
public class ParsePushApplication extends Application {

    /**
     * Log or request TAG
     */
    public static final String TAG = "VolleyPatterns";

    private static ParsePushApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the Parse SDK.
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "GbhsPA5Oh2yu2voFbxo45iJgFqPoJWd3kzZnqBZi", "diMBtwqe0Ysm0XXp2wb5fD5qpKusC1pkPeEgKDIQ");
        PushService.setDefaultPushCallback(this, MainActivity.class);
        //ParseObject.registerSubclass(Employee.class);
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
        Log.d("Start", "STY");
        ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Installation saved successfully");
                } else {
                    Log.e(TAG, "Installation failed to save: " + e);
                }
            }
        });

        Log.d("Start", "STYI");


       /*
        */
        //ParseInstallation.getCurrentInstallation().saveInBackground();



    }


}