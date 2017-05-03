package za.co.flatrocksolutions.frscodesample.profile.contract;

import java.util.ArrayList;

import za.co.flatrocksolutions.frscodesample.base.BasePresenter;
import za.co.flatrocksolutions.frscodesample.base.BaseView;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;

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
        void setInterests(ArrayList<String> interests);
        void showInterestsProgressbar();
        void hideInterestsProgressbar();
        void showAboutProgressbar();
        void hideAboutProgressbar();
    }

    interface Presenter extends BasePresenter<View> {
        void setUserProfile(UserProfile profile);
    }
}
