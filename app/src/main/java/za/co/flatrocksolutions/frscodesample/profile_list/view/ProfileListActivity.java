package za.co.flatrocksolutions.frscodesample.profile_list.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile.view.ProfileFragment;

public class ProfileListActivity extends AppCompatActivity {
    public static final String FLAG_ADD_FRAGMENT = "addFragment";
    public static final String LIST_FRAGMENT_TAG = ProfileListFragment.class.getSimpleName();
    public static final String DETAIL_FRAGMENT_TAG = ProfileFragment.class.getSimpleName();
    private FragmentManager mFragmentManager;

    public static Intent getLaunchIntent(Context context, boolean addFragment) {
        Intent i = new Intent(context, ProfileListActivity.class);
        i.putExtra(FLAG_ADD_FRAGMENT, addFragment);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        mFragmentManager = this.getSupportFragmentManager();

        if (getIntent().getBooleanExtra(FLAG_ADD_FRAGMENT, true)) {
            ProfileListFragment fragment = (ProfileListFragment) mFragmentManager.findFragmentByTag(LIST_FRAGMENT_TAG);

            if (fragment == null) {
                fragment = ProfileListFragment.newInstance();
            }

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.rootContainer, fragment, LIST_FRAGMENT_TAG)
                    .commit();
        }
    }

    public void loadUserDetailFragment(UserProfile profile) {
        ProfileFragment fragment = (ProfileFragment) mFragmentManager.findFragmentByTag(DETAIL_FRAGMENT_TAG);

        if (fragment == null) {
            fragment = ProfileFragment.newInstance(profile);
        }

        mFragmentManager
                .beginTransaction()
                .replace(R.id.rootContainer, fragment, LIST_FRAGMENT_TAG)
                .addToBackStack(LIST_FRAGMENT_TAG)
                .commit();
    }
}
