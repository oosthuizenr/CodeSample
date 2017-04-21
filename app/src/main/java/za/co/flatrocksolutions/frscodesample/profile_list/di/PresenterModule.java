package za.co.flatrocksolutions.frscodesample.profile_list.di;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.profile_list.presenter.ProfileListPresenter;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class PresenterModule {

    @ProfileListScope
    @Provides
    public ProfileListContract.Presenter providesProfileListPresenter(UserProfileInteractor userProfileInteractor, SchedulerProvider schedulerProvider) {
        return new ProfileListPresenter(userProfileInteractor, schedulerProvider);
    }
}
