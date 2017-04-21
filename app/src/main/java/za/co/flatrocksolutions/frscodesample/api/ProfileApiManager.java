package za.co.flatrocksolutions.frscodesample.api;

import java.util.ArrayList;

import io.reactivex.Observable;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;

/**
 * Created by renier on 4/20/2017.
 */

public class ProfileApiManager {
    private IProfileApi mProfileApi;

    public ProfileApiManager(IProfileApi mProfileApi) {
        this.mProfileApi = mProfileApi;
    }


    public Observable<ArrayList<UserProfile>> getUserProfiles() {
        return mProfileApi.getUserProfiles();
    }

    public Observable<String> getUserAbout(String userId) {
        return mProfileApi.getUserAbout(userId);
    }

    public Observable<ArrayList<String>> getUserInterests(String userId) {
        return mProfileApi.getUserInterests(userId);
    }
}
