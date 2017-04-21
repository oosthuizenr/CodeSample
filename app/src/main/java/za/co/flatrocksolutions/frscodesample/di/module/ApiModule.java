package za.co.flatrocksolutions.frscodesample.di.module;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import za.co.flatrocksolutions.frscodesample.api.IProfileApi;
import za.co.flatrocksolutions.frscodesample.api.ProfileApiManager;
import za.co.flatrocksolutions.frscodesample.di.qualifier.ProfileWebService;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class ApiModule {

    @FRSApplicationScope
    @Provides
    public IProfileApi providesProfileApi(@ProfileWebService Retrofit retrofit) {
        return retrofit.create(IProfileApi.class);
    }

    @FRSApplicationScope
    @Provides
    public ProfileApiManager providesProfileApiManager(IProfileApi profileApi) {
        return new ProfileApiManager(profileApi);
    }
}
