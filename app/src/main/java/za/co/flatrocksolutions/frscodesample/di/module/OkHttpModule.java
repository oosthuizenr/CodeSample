package za.co.flatrocksolutions.frscodesample.di.module;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;
import za.co.flatrocksolutions.frscodesample.di.qualifier.ApplicationContext;
import za.co.flatrocksolutions.frscodesample.di.qualifier.CacheFile;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class OkHttpModule {

    @FRSApplicationScope
    @Provides
    public HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> {
            Timber.i(message);
        });
    }

    @FRSApplicationScope
    @CacheFile
    @Provides
    public File providesCacheFile(@ApplicationContext Context context) {
        File f = new File(context.getCacheDir(), "okhttp_cache");
        f.mkdirs();

        return f;
    }

    @FRSApplicationScope
    @Provides
    public Cache providesCache(@CacheFile File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024); //10mb
    }

    @FRSApplicationScope
    @Provides
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }
}
