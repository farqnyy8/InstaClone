package com.vogella.android.rxjava.instaclone;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.parse.Parse;
import com.parse.ParseObject;
import com.vogella.android.rxjava.instaclone.theme.ThemeChangeHelper;
import com.vogella.android.rxjava.instaclone.theme.ThemeType;

public class InstaCloneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initAppTheme();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.PARSE_APPLICATION_ID)
                .clientKey(BuildConfig.PARSE_CLIENT_KEY)
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

    private void initAppTheme() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        ThemeType appTheme = ThemeType.getThemeType(
                sharedPreferences.getString(
                        ThemeChangeHelper.SAVED_THEME,
                        ThemeType.DefaultMode.toString()
                )
        );
        ThemeChangeHelper.setAppTheme(appTheme);
    }
}
