package za.co.flatrocksolutions.frscodesample.profile.contract;

import com.plumillonforge.android.chipview.Chip;

import java.util.ArrayList;

import za.co.flatrocksolutions.frscodesample.base.BasePresenter;
import za.co.flatrocksolutions.frscodesample.base.BaseView;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile.model.UserInterest;

/**
 * Created by renier on 4/20/2017.
 */

public interface ProfileContract {
    interface View extends BaseView {
        void setBackgroundImage(String uri);
        void setProfileImage(String uri);
        void setName(String name);
        void setTitle(String title);
        void setAbout(String about);
        void setInterests(ArrayList<Chip> interests);
        void showInterestsProgressbar();
        void hideInterestsProgressbar();
        void hideNoInterests();
        void showAboutProgressbar();
        void hideAboutProgressbar();
        void noInterests();
        void noAbout();
    }

    interface Presenter extends BasePresenter<View> {
        void setUserProfile(UserProfile profile);
    }
}
