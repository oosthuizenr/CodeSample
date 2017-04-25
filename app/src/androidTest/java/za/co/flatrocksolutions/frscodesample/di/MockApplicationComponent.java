package za.co.flatrocksolutions.frscodesample.di;

import dagger.Component;
import za.co.flatrocksolutions.frscodesample.di.component.ApplicationComponent;
import za.co.flatrocksolutions.frscodesample.di.module.ApiModule;
import za.co.flatrocksolutions.frscodesample.di.module.ApplicationModule;
import za.co.flatrocksolutions.frscodesample.di.module.PicassoModule;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.profile_list.adapter.UserProfileListAdapter;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/25/2017.
 */
@FRSApplicationScope
@Component(modules = {
        ApplicationModule.class,
        OkHttpModule.class,
        PicassoModule.class,
        InteractorModule.class,
        RetrofitModule.class,
        GsonModule.class,
        ApiModule.class,
        RxModule.class})
public interface MockApplicationComponent extends ApplicationComponent {
    UserProfileInteractor exposeUserProfileInteractor();
    SchedulerProvider exposeSchedulerProvider();

    void inject(UserProfileListAdapter.UserProfileListViewHolder viewHolder);

}
