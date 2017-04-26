package za.co.flatrocksolutions.frscodesample.di.profilelist;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.profile_list.di.ProfileListScope;
import za.co.flatrocksolutions.frscodesample.profile_list.presenter.ProfileListPresenter;

/**
 * Created by renier on 4/26/2017.
 */
@Module
public class MockPresenterModule {
    @ProfileListScope
    @Provides
    public ProfileListContract.Presenter providesProfileListPresenter() {
        return Mockito.mock(ProfileListPresenter.class);
    }
}
