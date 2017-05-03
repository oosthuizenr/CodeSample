package za.co.flatrocksolutions.frscodesample.profile.di;


import dagger.Component;
import za.co.flatrocksolutions.frscodesample.di.component.ApplicationComponent;
import za.co.flatrocksolutions.frscodesample.profile.view.ProfileFragment;

/**
 * Created by renier on 5/3/2017.
 */
@ProfileScope
@Component(
        modules = { PresenterModule.class},
        dependencies = ApplicationComponent.class
)
public interface ProfileComponent {


    void inject(ProfileFragment fragment);
}
