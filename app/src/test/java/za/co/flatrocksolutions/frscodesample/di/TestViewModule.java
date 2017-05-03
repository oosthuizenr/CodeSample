package za.co.flatrocksolutions.frscodesample.di;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.profile.contract.ProfileContract;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class TestViewModule {
    @TestScope
    @Provides
    public ProfileListContract.View providesProfileListView() {
        return Mockito.mock(ProfileListContract.View.class);
    }


    @TestScope
    @Provides
    public ProfileContract.View providesProfileView() {
        return Mockito.mock(ProfileContract.View.class);
    }
}
