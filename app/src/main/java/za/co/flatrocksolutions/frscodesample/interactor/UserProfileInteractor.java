package za.co.flatrocksolutions.frscodesample.interactor;

import java.util.ArrayList;

import io.reactivex.Observable;
import za.co.flatrocksolutions.frscodesample.api.ProfileApiManager;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;

/**
 * Created by renier on 4/20/2017.
 */

public class UserProfileInteractor {
    private ProfileApiManager mProfileApiManager;

    public UserProfileInteractor(ProfileApiManager mProfileApiManager) {
        this.mProfileApiManager = mProfileApiManager;
    }

    public Observable<ArrayList<UserProfile>> getListOfUsers() {
        return mProfileApiManager.getUserProfiles();
    }

    public Observable<String> getUserAbout(String userId) {
        return mProfileApiManager.getUserAbout(userId);
    }

    public Observable<ArrayList<String>> getUserInterests(String userId) {
        return mProfileApiManager.getUserInterests(userId);
    }
}
