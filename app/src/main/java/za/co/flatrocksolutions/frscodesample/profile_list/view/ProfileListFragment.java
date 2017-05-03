package za.co.flatrocksolutions.frscodesample.profile_list.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.flatrocksolutions.frscodesample.FRSApplication;
import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.RecyclerItemClickListener;
import za.co.flatrocksolutions.frscodesample.di.DIHelper;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile_list.adapter.UserProfileListAdapter;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.profile_list.di.DaggerProfileListComponent;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileListFragment extends Fragment implements ProfileListContract.View {

    @Inject
    ProfileListContract.Presenter mPresenter;

    @Inject
    UserProfileListAdapter mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tvNoUsers)
    TextView tvNoUsers;

    @BindString(R.string.generic_error)
    String mGenericError;

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

        DIHelper.getProfileListComponent(getContext(), DIHelper.getAppComponent(getContext())).inject(this);

        ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        mPresenter.profileClicked(mAdapter.getData().get(position));
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void noUsersToShow() {
        tvNoUsers.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void setUsersToShow(ArrayList<UserProfile> model) {
        tvNoUsers.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        mAdapter.setData(model);
    }

    @Override
    public void launchUserProfileDetail(UserProfile profile) {
        ((ProfileListActivity)getActivity()).loadUserDetailFragment(profile);
    }

    @Override
    public void hideNoUsers() {
        tvNoUsers.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        Snackbar.make(recyclerView, mGenericError, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_retry, (view) -> mPresenter.retryClicked())
                .show();
    }

    @Override
    public void onError(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_retry, (view) -> mPresenter.retryClicked())
                .show();
    }
}
