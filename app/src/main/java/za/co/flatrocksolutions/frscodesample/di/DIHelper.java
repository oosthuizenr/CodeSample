package za.co.flatrocksolutions.frscodesample.di;

import android.content.Context;

import za.co.flatrocksolutions.frscodesample.FRSApplication;
import za.co.flatrocksolutions.frscodesample.di.component.ApplicationComponent;
import za.co.flatrocksolutions.frscodesample.di.component.DaggerApplicationComponent;
import za.co.flatrocksolutions.frscodesample.di.module.ApplicationModule;
import za.co.flatrocksolutions.frscodesample.profile.di.DaggerProfileComponent;
import za.co.flatrocksolutions.frscodesample.profile.di.ProfileComponent;
import za.co.flatrocksolutions.frscodesample.profile_list.di.DaggerProfileListComponent;
import za.co.flatrocksolutions.frscodesample.profile_list.di.ProfileListComponent;

/**
 * Created by renier on 4/25/2017.
 */

public class DIHelper {
    private static ApplicationComponent mAppComponent;
    private static ProfileListComponent mProfileListComponent;
    private static ProfileComponent mProfileComponent;


    public static ApplicationComponent getAppComponent(Context context) {
        if (mAppComponent == null) {
            mAppComponent = DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(FRSApplication.from(context)))
                    .build();
        }

        return mAppComponent;
    }

    public static void setAppComponent(ApplicationComponent appComponent) {
        mAppComponent = appComponent;
    }

    public static ProfileListComponent getProfileListComponent(Context context, ApplicationComponent appComponent) {
        if (mProfileListComponent == null) {
            mProfileListComponent = DaggerProfileListComponent
                    .builder()
                    .applicationComponent(appComponent)
                    .build();
        }

        return mProfileListComponent;
    }

    public static void setProfileListComponent(ProfileListComponent profileListComponent) {
        mProfileListComponent = profileListComponent;
    }

    public static ProfileComponent getProfileComponent(Context context, ApplicationComponent appComponent) {
        if (mProfileComponent == null) {
            mProfileComponent = DaggerProfileComponent
                    .builder()
                    .applicationComponent(appComponent)
                    .build();
        }

        return mProfileComponent;
    }

    public static void setProfileComponent(ProfileComponent profileComponent) {
        mProfileComponent = profileComponent;
    }
}
