package za.co.flatrocksolutions.frscodesample.di.module;

import dagger.Module;
import dagger.Provides;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;
import za.co.flatrocksolutions.frscodesample.rx.AppSchedulerProvider;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/21/2017.
 */
@Module
public class RxModule {

    @FRSApplicationScope
    @Provides
    public SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
