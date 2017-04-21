package za.co.flatrocksolutions.frscodesample.di;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class TestInteractorModule {

    @TestScope
    @Provides
    public UserProfileInteractor providesUserProfileInteractor() {
        return Mockito.mock(UserProfileInteractor.class);
    }
}
