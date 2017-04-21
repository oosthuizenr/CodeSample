package za.co.flatrocksolutions.frscodesample.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class GsonModule {
    @FRSApplicationScope
    @Provides
    public Gson providesGson() {
        return new GsonBuilder().create();
    }
}
