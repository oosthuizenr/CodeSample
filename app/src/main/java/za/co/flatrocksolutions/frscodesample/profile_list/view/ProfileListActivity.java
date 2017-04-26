package za.co.flatrocksolutions.frscodesample.profile_list.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import za.co.flatrocksolutions.frscodesample.R;

public class ProfileListActivity extends AppCompatActivity {
    public static final String FLAG_ADD_FRAGMENT = "addFragment";
    public static final String FRAGMENT_TAG = ProfileListActivity.class.getSimpleName();
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
            ProfileListFragment fragment = (ProfileListFragment) mFragmentManager.findFragmentByTag(FRAGMENT_TAG);

            if (fragment == null) {
                fragment = ProfileListFragment.newInstance();
            }

            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.rootContainer, fragment, FRAGMENT_TAG)
                    .commit();
        }
    }
}
