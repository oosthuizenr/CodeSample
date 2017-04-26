package za.co.flatrocksolutions.frscodesample;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.di.DIHelper;
import za.co.flatrocksolutions.frscodesample.di.profilelist.DaggerMockProfileListComponent;
import za.co.flatrocksolutions.frscodesample.di.profilelist.MockPresenterModule;
import za.co.flatrocksolutions.frscodesample.di.profilelist.MockProfileListComponent;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.profile_list.presenter.ProfileListPresenter;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListActivity;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListFragment;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.IsNot.not;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by renier on 4/24/2017.
 */
public class ProfileListFragmentTest {
    @Rule
    public ActivityTestRule<ProfileListActivity> activityTestRule =
            new ActivityTestRule<>(ProfileListActivity.class, true, false);

    @Inject
    ProfileListContract.Presenter mPresenter;

    private ProfileListFragment mFragment;

    @Before
    public void before() {
        MockProfileListComponent component = DaggerMockProfileListComponent.builder().applicationComponent(DIHelper.getAppComponent(InstrumentationRegistry.getTargetContext())).build();
        DIHelper.setProfileListComponent(component);

        component.inject(this);

        activityTestRule.launchActivity(ProfileListActivity.getLaunchIntent(getTargetContext(), false));
    }

    @Test
    public void testInitialState() {
        doNothing().when(mPresenter).setView(any());
        doNothing().when(mPresenter).onSubscribe();

        setupFragment();

        onView(withId(R.id.tvNoUsers)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
    }


    @Test
    public void testLoadingState() {
        doNothing().when(mPresenter).setView(any());
        doNothing().when(mPresenter).onSubscribe();

        setupFragment();

        onView(withId(R.id.tvNoUsers)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
    }

    @Test
    public void testErrorState() {
        doNothing().when(mPresenter).setView(any());

        doAnswer(invocation -> {
            ProfileListContract.View view = mFragment;

            view.onError();

            return null;
        }).when(mPresenter).onSubscribe();

        setupFragment();

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(getTargetContext().getString(R.string.generic_error))))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(withId(android.support.design.R.id.snackbar_action))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    @Test
    public void testErrorStateWithMessage() {
        String message = "My Error";

        doNothing().when(mPresenter).setView(any());

        doAnswer(invocation -> {
            ProfileListContract.View view = mFragment;

            view.onError(message);

            return null;
        }).when(mPresenter).onSubscribe();

        setupFragment();

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(message)))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(withId(android.support.design.R.id.snackbar_action))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    @Test
    public void testLoadedStateWithUsers() {
        doNothing().when(mPresenter).setView(any());

        try {
            InputStream is = getInstrumentation().getContext().getResources().getAssets().open("ic_account_black_48dp.png");

            try {
                File file = new File(getInstrumentation().getContext().getCacheDir(), "ic_account_black_48dp.png");
                OutputStream output = new FileOutputStream(file);
                try {
                    try {
                        byte[] buffer = new byte[4 * 1024]; // or other buffer size
                        int read;

                        while ((read = is.read(buffer)) != -1) {
                            output.write(buffer, 0, read);
                        }
                        output.flush();
                    } finally {
                        output.close();
                    }

                    ArrayList<UserProfile> model = new ArrayList<>();
                    model.add(new UserProfile(UUID.randomUUID().toString(),
                            "John",
                            file.getAbsolutePath(),
                            "Mr"));
                    model.add(new UserProfile(UUID.randomUUID().toString(),
                            "Jane",
                            file.getAbsolutePath(),
                            "Ms"));

                    doAnswer(invocation -> {
                        ProfileListContract.View view = mFragment;


                        view.setUsersToShow(model);

                        return null;
                    }).when(mPresenter).onSubscribe();

                    setupFragment();

                    onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("")))
                            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
                    onView(withId(android.support.design.R.id.snackbar_action))
                            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));



                } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
                }
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }


    private void setupFragment() {
        FragmentManager fragmentManager = activityTestRule.getActivity().getSupportFragmentManager();

        mFragment = (ProfileListFragment) fragmentManager.findFragmentByTag(ProfileListActivity.FRAGMENT_TAG);

        if (mFragment == null) {
            mFragment = ProfileListFragment.newInstance();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.rootContainer, mFragment, ProfileListActivity.FRAGMENT_TAG)
                .commit();
    }
}