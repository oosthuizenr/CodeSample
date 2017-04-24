package za.co.flatrocksolutions.frscodesample.profile_list.di;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.profile_list.adapter.UserProfileListAdapter;

/**
 * Created by renier on 4/20/2017.
 */
@Module
public class AdapterModule {
    @ProfileListScope
    @Provides
    public UserProfileListAdapter providesUserProfileListAdapter() {
        return new UserProfileListAdapter();
    }
}
