package za.co.flatrocksolutions.frscodesample.di.module;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.flatrocksolutions.frscodesample.di.qualifier.ProfileWebService;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class RetrofitModule {
    @FRSApplicationScope
    @Provides
    public RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @FRSApplicationScope
    @Provides
    public GsonConverterFactory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @FRSApplicationScope
    @ProfileWebService
    @Provides
    public Retrofit providesProfileWebService(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .baseUrl("https://testprofiler.mobila.co.za")
                .build();
    }

}
