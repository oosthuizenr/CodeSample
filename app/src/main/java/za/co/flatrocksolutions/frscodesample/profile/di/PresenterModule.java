package za.co.flatrocksolutions.frscodesample.profile.di;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.profile.contract.ProfileContract;
import za.co.flatrocksolutions.frscodesample.profile.presenter.ProfilePresenter;

/**
 * Created by renier on 5/3/2017.
 */
@Module
public class PresenterModule {
    @ProfileScope
    @Provides
    public ProfileContract.Presenter providesProfilePresenter(UserProfileInteractor profileInteractor) {
        return new ProfilePresenter(profileInteractor);
    }
}
