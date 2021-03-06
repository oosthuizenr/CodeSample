package za.co.flatrocksolutions.frscodesample.di;


import dagger.Component;
import za.co.flatrocksolutions.frscodesample.ProfileListPresenterTest;

/**
 * Created by renier on 4/20/2017.
 */
@TestScope
@Component (modules = { TestInteractorModule.class, TestViewModule.class, TestPresenterModule.class, TestRxModule.class})
public interface TestComponent {
    void inject(ProfileListPresenterTest test);

}
