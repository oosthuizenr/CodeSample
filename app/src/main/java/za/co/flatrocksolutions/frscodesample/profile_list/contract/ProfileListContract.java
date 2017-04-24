package za.co.flatrocksolutions.frscodesample.profile_list.contract;

import java.util.ArrayList;

import za.co.flatrocksolutions.frscodesample.base.BasePresenter;
import za.co.flatrocksolutions.frscodesample.base.BaseView;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;

/**
 * Created by renier on 4/20/2017.
 */

public interface ProfileListContract {
    interface View extends BaseView {
        void showProgressBar();
        void hideProgressBar();
        void noUsersToShow();
        void setUsersToShow(ArrayList<UserProfile> model);
        void launchUserProfileDetail(UserProfile profile);
    }

    interface Presenter extends BasePresenter<View> {
        void profileClicked(UserProfile profile);
        void retryClicked();
    }
}
