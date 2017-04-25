package za.co.flatrocksolutions.frscodesample;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;
import za.co.flatrocksolutions.frscodesample.di.component.ApplicationComponent;
import za.co.flatrocksolutions.frscodesample.di.component.DaggerApplicationComponent;
import za.co.flatrocksolutions.frscodesample.di.module.ApplicationModule;

/**
 * Created by renier on 4/20/2017.
 */

public class FRSApplication extends Application {
/*

    private ApplicationComponent mApplicationComponent;
*/

    /**
     * Helper method to get access to {@link FRSApplication} from a {@link Context}.
     * @param context The {@link Context} to cast
     * @return The {@link FRSApplication} cast from context
     * @throws IllegalArgumentException
     */
    public static FRSApplication from(Context context) throws IllegalArgumentException{
        if (context.getApplicationContext() instanceof FRSApplication) {
            return (FRSApplication) context.getApplicationContext();
        } else {
            throw new IllegalArgumentException("Not an instance of FRSApplication");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Plant a debug tree so that all log messages goes to console
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());


       /* mApplicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .build();*/
    }

    /*public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }*/
}
