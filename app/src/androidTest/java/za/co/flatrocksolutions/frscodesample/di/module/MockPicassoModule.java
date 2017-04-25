package za.co.flatrocksolutions.frscodesample.di.module;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import za.co.flatrocksolutions.frscodesample.di.qualifier.ApplicationContext;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class MockPicassoModule {

    @FRSApplicationScope
    @Provides
    public Picasso providesPicasso() {
        return new Picasso.Builder(context)
                .downloader(downloader)
                .build();
    }
}
