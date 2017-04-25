package za.co.flatrocksolutions.frscodesample;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListActivity;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.IsNot.not;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by renier on 4/24/2017.
 */
public class ProfileListFragmentTest {
    @Rule
    public ActivityTestRule<ProfileListActivity> activityTestRule =
            new ActivityTestRule<>(ProfileListActivity.class);

    @Test
    public void validateVisibility() {
        onView(withId(R.id.tvNoUsers)).check(matches(not(isDisplayed())));
    }
}