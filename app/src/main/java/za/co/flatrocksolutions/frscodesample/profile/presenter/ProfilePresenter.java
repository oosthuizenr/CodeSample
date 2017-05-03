package za.co.flatrocksolutions.frscodesample.profile.presenter;

import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile.contract.ProfileContract;

/**
 * Created by renier on 5/3/2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {


    private UserProfileInteractor mUserProfileInteractor;
    private ProfileContract.View mView;

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

    }

    @Override
    public void setUserProfile(UserProfile profile) {
        mView.setBackgroundImage(profile.getBackgroundPictureUrl());
        mView.setProfileImage(profile.getProfilePictureUrl());
        mView.setName(profile.getName());
        mView.setTitle(profile.getTitle());
    }
}
