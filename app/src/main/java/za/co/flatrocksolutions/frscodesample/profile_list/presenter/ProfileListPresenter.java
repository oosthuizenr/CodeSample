package za.co.flatrocksolutions.frscodesample.profile_list.presenter;

import io.reactivex.disposables.CompositeDisposable;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/20/2017.
 */

public class ProfileListPresenter implements ProfileListContract.Presenter {
    private ProfileListContract.View mView;
    private UserProfileInteractor mUserProfileInteractor;
    private SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public ProfileListPresenter(UserProfileInteractor mUserProfileInteractor, SchedulerProvider mSchedulerProvider) {
        this.mUserProfileInteractor = mUserProfileInteractor;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void setView(ProfileListContract.View view) {
        this.mView = view;
    }

    @Override
    public void onSubscribe() {
        loadUsers();
    }

    private void loadUsers() {
        mView.showProgressBar();

        mCompositeDisposable.add(mUserProfileInteractor.getListOfUsers()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(result -> {
                    mView.hideProgressBar();

                    if (result != null && result.size() > 0) {
                        mView.setUsersToShow(result);
                    } else {
                        mView.noUsersToShow();
                    }
                }, error -> {
                    mView.hideProgressBar();
                    mView.noUsersToShow();

                    mView.onError();
                }));
    }


    @Override
    public void profileClicked(UserProfile profile) {
        mView.launchUserProfileDetail(profile);
    }


    @Override
    public void retryClicked() {
        loadUsers();
    }

    @Override
    public void onUnsubscribe() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.clear();
    }
}
