package za.co.flatrocksolutions.frscodesample.interactor;

import com.plumillonforge.android.chipview.Chip;

import java.util.ArrayList;

import io.reactivex.Observable;
import za.co.flatrocksolutions.frscodesample.api.ProfileApiManager;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile.model.UserInterest;

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

    public Observable<ArrayList<Chip>> getUserInterests(String userId) {
        return mProfileApiManager.getUserInterests(userId)
                .flatMap(interests -> {
                    ArrayList<Chip> toReturn = new ArrayList<>();

                    for (String interest : interests) {
                        toReturn.add(new UserInterest(interest));
                    }

                    return Observable.just(toReturn);
                });
    }
}
