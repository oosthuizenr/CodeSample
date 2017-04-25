package za.co.flatrocksolutions.frscodesample.di;

import dagger.Component;
import za.co.flatrocksolutions.frscodesample.profile_list.di.AdapterModule;
import za.co.flatrocksolutions.frscodesample.profile_list.di.PresenterModule;
import za.co.flatrocksolutions.frscodesample.profile_list.di.ProfileListComponent;
import za.co.flatrocksolutions.frscodesample.profile_list.di.ProfileListScope;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListFragment;

/**
 * Created by renier on 4/25/2017.
 */
@ProfileListScope
@Component(modules = {AdapterModule.class, PresenterModule.class}, dependencies = MockApplicationComponent.class)
public interface MockProfileListComponent extends ProfileListComponent {
    void inject(ProfileListFragment fragment);
}
