package za.co.flatrocksolutions.frscodesample.di.profilelist;

import dagger.Component;
import za.co.flatrocksolutions.frscodesample.ProfileListFragmentTest;
import za.co.flatrocksolutions.frscodesample.di.component.ApplicationComponent;
import za.co.flatrocksolutions.frscodesample.profile_list.di.AdapterModule;
import za.co.flatrocksolutions.frscodesample.profile_list.di.ProfileListComponent;
import za.co.flatrocksolutions.frscodesample.profile_list.di.ProfileListScope;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListFragment;

/**
 * Created by renier on 4/25/2017.
 */
@ProfileListScope
@Component(modules = {AdapterModule.class, MockPresenterModule.class}, dependencies = ApplicationComponent.class)
public interface MockProfileListComponent extends ProfileListComponent {
    void inject(ProfileListFragment fragment);
    void inject(ProfileListFragmentTest test);
}
