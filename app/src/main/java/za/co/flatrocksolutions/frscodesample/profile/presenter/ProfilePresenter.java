package za.co.flatrocksolutions.frscodesample.profile.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile.contract.ProfileContract;

/**
 * Created by renier on 5/3/2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {


    private UserProfileInteractor mUserProfileInteractor;
    private ProfileContract.View mView;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public ProfilePresenter(UserProfileInteractor userProfileInteractor) {
        mUserProfileInteractor = userProfileInteractor;
    }

    @Override
    public void setView(ProfileContract.View view) {
        mView = view;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onUnsubscribe() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.clear();
    }

    @Override
    public void setUserProfile(UserProfile profile) {
        mView.setBackgroundImage(profile.getBackgroundPictureUrl());
        mView.setProfileImage(profile.getProfilePictureUrl());
        mView.setName(profile.getName());
        mView.setTitle(profile.getTitle());

        mView.showAboutProgressbar();
        mView.showInterestsProgressbar();

        mCompositeDisposable.add(mUserProfileInteractor.getUserAbout(profile.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    mView.hideAboutProgressbar();
                    mView.setAbout(result);
                }, error -> {
                    mView.onError();
                }));

        mCompositeDisposable.add(mUserProfileInteractor.getUserInterests(profile.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    mView.hideAboutProgressbar();
                    mView.setInterests(result);
                }, error -> {
                    mView.onError();
                }));
    }
}
