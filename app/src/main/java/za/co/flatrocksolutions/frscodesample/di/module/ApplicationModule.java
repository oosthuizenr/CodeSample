package za.co.flatrocksolutions.frscodesample.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.FRSApplication;
import za.co.flatrocksolutions.frscodesample.di.qualifier.ApplicationContext;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class ApplicationModule {
    private FRSApplication mApplication;

    public ApplicationModule(FRSApplication mApplication) {
        this.mApplication = mApplication;
    }

    @FRSApplicationScope
    @Provides
    public FRSApplication providesApplication() {
        return mApplication;
    }

    @FRSApplicationScope
    @ApplicationContext
    @Provides
    public Context providesApplicationContext() { return mApplication.getApplicationContext(); }
}
