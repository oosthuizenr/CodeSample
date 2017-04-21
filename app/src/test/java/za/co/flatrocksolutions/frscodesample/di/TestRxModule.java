package za.co.flatrocksolutions.frscodesample.di;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.TestSchedulerProvider;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/21/2017.
 */
@Module
public class TestRxModule {
    @TestScope
    @Provides
    public SchedulerProvider providesSchedulerProvider() {
        return new TestSchedulerProvider();
    }
}
