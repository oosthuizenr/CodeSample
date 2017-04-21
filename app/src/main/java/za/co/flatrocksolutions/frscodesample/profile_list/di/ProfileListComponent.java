package za.co.flatrocksolutions.frscodesample.profile_list.di;


import dagger.Component;
import za.co.flatrocksolutions.frscodesample.di.component.ApplicationComponent;
import za.co.flatrocksolutions.frscodesample.profile_list.view.ProfileListFragment;

/**
 * Created by renier on 4/20/2017.
 */
@ProfileListScope
@Component(
        modules = { PresenterModule.class, AdapterModule.class },
        dependencies = ApplicationComponent.class
)
public interface ProfileListComponent {
    void inject(ProfileListFragment fragment);
}
