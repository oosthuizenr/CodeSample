package za.co.flatrocksolutions.frscodesample.di;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.profile.presenter.ProfilePresenter;
import za.co.flatrocksolutions.frscodesample.profile_list.presenter.ProfileListPresenter;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class TestPresenterModule {

    @TestScope
    @Provides
    public ProfileListPresenter providesProfileListPresenter(UserProfileInteractor interactor, SchedulerProvider schedulerProvider) {
        return new ProfileListPresenter(interactor, schedulerProvider);
    }

    @TestScope
    @Provides
    public ProfilePresenter providesProfilePresenter(UserProfileInteractor interactor) {
        return new ProfilePresenter(interactor);
    }
}
