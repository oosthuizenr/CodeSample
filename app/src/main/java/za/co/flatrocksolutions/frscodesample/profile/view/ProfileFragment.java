package za.co.flatrocksolutions.frscodesample.profile.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.di.DIHelper;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile.contract.ProfileContract;

public class ProfileFragment extends Fragment implements ProfileContract.View, AppBarLayout.OnOffsetChangedListener {
    private static final String USER_PROFILE_KEY = "UserProfile";

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    @Inject
    ProfileContract.Presenter mPresenter;

    @Inject
    Picasso mPicasso;

    @BindView(R.id.rootContainer)
    View rootContainer;

    @BindView(R.id.llNameContainer)
    LinearLayout mTitleContainer;

    @BindView(R.id.tvToolbarTitle)
    TextView tvToolbarTitle;

    @BindView(R.id.mainAppBar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.mainToolbar)
    Toolbar mToolbar;

    @BindView(R.id.civProfile)
    CircleImageView civProfile;

    @BindView(R.id.ivPlaceholder)
    ImageView ivPlaceholder;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvAbout)
    TextView tvAbout;

    @BindView(R.id.pbAbout)
    ProgressBar pbAbout;

    @BindView(R.id.pbInterests)
    ProgressBar pbInterests;

    @BindView(R.id.chipView)
    ChipView chipView;

    @BindView(R.id.tvNoInterests)
    TextView tvNoInterests;



    @BindString(R.string.generic_error)
    String mGenericError;

    public static ProfileFragment newInstance(UserProfile profile) {
        ProfileFragment fragment = new ProfileFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(USER_PROFILE_KEY, profile);

        fragment.setArguments(arguments);

        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        DIHelper.getProfileComponent(getContext(), DIHelper.getAppComponent(getContext())).inject(this);
        ButterKnife.bind(this, v);

        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(tvToolbarTitle, 0, View.INVISIBLE);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.setView(this);
        mPresenter.onSubscribe();

        mPresenter.setUserProfile(getArguments().getParcelable(USER_PROFILE_KEY));
    }

    @Override
    public void onDestroy() {
        mPresenter.onUnsubscribe();

        super.onDestroy();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }


    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(tvToolbarTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(tvToolbarTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public void onError() {
        Snackbar.make(rootContainer, mGenericError, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onError(String message) {
        Snackbar.make(rootContainer, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setBackgroundImage(String uri) {
        mPicasso
                .load(uri)
                .into(ivPlaceholder);
    }

    @Override
    public void setProfileImage(String uri) {
        mPicasso
                .load(uri)
                .noFade()
                .into(civProfile);
    }

    @Override
    public void setName(String name) {
        tvToolbarTitle.setText(name);
        tvName.setText(name);
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setAbout(String about) {
        tvAbout.setText(about);
    }

    @Override
    public void setInterests(ArrayList<Chip> interests) {
        chipView.setChipList(interests);
    }

    @Override
    public void showInterestsProgressbar() {
        pbInterests.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInterestsProgressbar() {
        pbInterests.setVisibility(View.GONE);
    }

    @Override
    public void showAboutProgressbar() {
        pbAbout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAboutProgressbar() {
        pbAbout.setVisibility(View.GONE);
    }

    @Override
    public void noInterests() {
        tvNoInterests.setVisibility(View.VISIBLE);
        chipView.setVisibility(View.GONE);
    }

    @Override
    public void hideNoInterests() {
        tvNoInterests.setVisibility(View.GONE);
        chipView.setVisibility(View.VISIBLE);
    }

    @Override
    public void noAbout() {
        tvAbout.setText(R.string.no_about);
    }
}
