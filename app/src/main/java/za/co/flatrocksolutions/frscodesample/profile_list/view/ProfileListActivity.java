package za.co.flatrocksolutions.frscodesample.profile_list.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import za.co.flatrocksolutions.frscodesample.R;

public class ProfileListActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = ProfileListActivity.class.getSimpleName();
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        mFragmentManager = this.getSupportFragmentManager();

        ProfileListFragment fragment = (ProfileListFragment) mFragmentManager.findFragmentByTag(FRAGMENT_TAG);

        if (fragment == null){
            fragment = ProfileListFragment.newInstance();
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.rootContainer, fragment, FRAGMENT_TAG)
                .commit();
    }
}
