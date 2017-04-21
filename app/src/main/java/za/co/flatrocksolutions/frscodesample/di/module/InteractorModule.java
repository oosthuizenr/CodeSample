package za.co.flatrocksolutions.frscodesample.di.module;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.api.ProfileApiManager;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class InteractorModule {

    @Provides
    @FRSApplicationScope
    public UserProfileInteractor providesUserProfileInteractor(ProfileApiManager profileApiManager) {
        return new UserProfileInteractor(profileApiManager);
    }
}
