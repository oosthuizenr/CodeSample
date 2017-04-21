package za.co.flatrocksolutions.frscodesample.profile_list.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import za.co.flatrocksolutions.frscodesample.FRSApplication;
import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.profile_list.di.DaggerProfileListComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileListFragment extends Fragment implements ProfileListContract.View {

    @Inject
    ProfileListContract.Presenter mPresenter;

    public ProfileListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_list, container, false);

        DaggerProfileListComponent
                .builder()
                .applicationComponent(FRSApplication.from(getContext()).getApplicationComponent())
                .build()
                .inject(this);

        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.setView(this);
        mPresenter.onSubscribe();
    }

    @Override
    public void onDestroy() {
        mPresenter.onUnsubscribe();

        super.onDestroy();
    }

    public static ProfileListFragment newInstance() {
        return new ProfileListFragment();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void noUsersToShow() {

    }

    @Override
    public void setUsersToShow(ArrayList<UserProfile> model) {

    }

    @Override
    public void launchUserProfileDetail(UserProfile profile) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onError(String message) {

    }
}
