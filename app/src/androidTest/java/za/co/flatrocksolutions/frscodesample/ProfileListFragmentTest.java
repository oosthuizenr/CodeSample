package za.co.flatrocksolutions.frscodesample;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import za.co.flatrocksolutions.frscodesample.di.DIHelper;
import za.co.flatrocksolutions.frscodesample.di.profilelist.DaggerMockProfileListComponent;
import za.co.flatrocksolutions.frscodesample.di.profilelist.MockProfileListComponent;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListActivity;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListFragment;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


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
        onView(withId(R.id.tvNoUsers))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
        onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
        onView(withId(R.id.progressBar))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    @Test
    public void testHideProgressBar() {
        setupFragment();
        ProfileListContract.View view = mFragment;

        mFragment.getActivity().runOnUiThread(() -> view.hideProgressBar());

        onView(withId(R.id.progressBar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    public void testShowProgressBar() {
        setupFragment();

        ProfileListContract.View view = mFragment;
        mFragment.getActivity().runOnUiThread(() -> {
            view.hideProgressBar();
            view.showProgressBar();
        });

        onView(withId(R.id.progressBar))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    @Test
    public void testNoUsersToShow() {
        setupFragment();

        ProfileListContract.View view = mFragment;
        mFragment.getActivity().runOnUiThread(() -> view.noUsersToShow());

        onView(withId(R.id.tvNoUsers))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));

        onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    public void testUsersToShow() throws Exception {
        doNothing().when(mPresenter).setView(any());

        String fileUri = getProfileIconUri();

        ArrayList<UserProfile> model = new ArrayList<>();
        model.add(new UserProfile(UUID.randomUUID().toString(),
                "John",
                fileUri,
                fileUri,
                "Mr"));
        model.add(new UserProfile(UUID.randomUUID().toString(),
                "Jane",
                fileUri,
                fileUri,
                "Ms"));

        doAnswer(invocation -> {
            ProfileListContract.View view = mFragment;

            view.setUsersToShow(model);

            return null;
        }).when(mPresenter).onSubscribe();

        setupFragment();

        onView(withId(R.id.tvNoUsers))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));

        onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }

    @Test
    public void testAdapterLayout() throws Exception {
        doNothing().when(mPresenter).setView(any());

        String fileUri = getProfileIconUri();

        ArrayList<UserProfile> model = new ArrayList<>();
        model.add(new UserProfile(UUID.randomUUID().toString(),
                "John",
                fileUri,
                fileUri,
                "Mr"));
        model.add(new UserProfile(UUID.randomUUID().toString(),
                "Jane",
                fileUri,
                fileUri,
                "Ms"));

        doAnswer(invocation -> {
            ProfileListContract.View view = mFragment;

            view.setUsersToShow(model);

            return null;
        }).when(mPresenter).onSubscribe();

        setupFragment();

        onView(withRecyclerView(R.id.recyclerView).atPosition(0))
                .check(matches(hasDescendant(withText(model.get(0).getName()))));
        onView(withRecyclerView(R.id.recyclerView).atPosition(0))
                .check(matches(hasDescendant(withText(model.get(0).getTitle()))));

        onView(withRecyclerView(R.id.recyclerView).atPosition(1))
                .check(matches(hasDescendant(withText(model.get(1).getName()))));
        onView(withRecyclerView(R.id.recyclerView).atPosition(1))
                .check(matches(hasDescendant(withText(model.get(1).getTitle()))));
    }



    @Test
    public void testOnErrorGeneric() {
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
    public void testOnErrorWithMessage() {
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
    public void testRecyclerViewItemClicked() throws Exception {
        doNothing().when(mPresenter).setView(any());
        doNothing().when(mPresenter).profileClicked(any());

        String fileUri = getProfileIconUri();

        ArrayList<UserProfile> model = new ArrayList<>();
        model.add(new UserProfile(UUID.randomUUID().toString(),
                "John",
                fileUri,
                fileUri,
                "Mr"));
        model.add(new UserProfile(UUID.randomUUID().toString(),
                "Jane",
                fileUri,
                fileUri,
                "Ms"));

        doAnswer(invocation -> {
            ProfileListContract.View view = mFragment;

            view.setUsersToShow(model);

            return null;
        }).when(mPresenter).onSubscribe();

        setupFragment();

        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition(1, click()));

        verify(mPresenter).profileClicked(any());
    }

    private String getProfileIconUri() throws Exception {
        File file = new File(getInstrumentation().getTargetContext().getCacheDir(), "ic_account_black_48dp2.png");

        if (!file.exists()) {
            InputStream is = getInstrumentation().getContext().getResources().getAssets().open("ic_account_black_48dp.png");

            OutputStream output = new FileOutputStream(file);

            try {
                byte[] buffer = new byte[4 * 1024];
                int read;

                while ((read = is.read(buffer)) != -1) {
                    output.write(buffer, 0, read);
                }
                output.flush();
            } finally {
                output.close();
                is.close();
            }
        }

        return file.getAbsolutePath();
    }


    private void setupFragment() {
        FragmentManager fragmentManager = activityTestRule.getActivity().getSupportFragmentManager();

        mFragment = (ProfileListFragment) fragmentManager.findFragmentByTag(ProfileListActivity.LIST_FRAGMENT_TAG);

        if (mFragment == null) {
            mFragment = ProfileListFragment.newInstance();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.rootContainer, mFragment, ProfileListActivity.LIST_FRAGMENT_TAG)
                .commit();

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
    }


    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}