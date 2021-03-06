package za.co.flatrocksolutions.frscodesample.di.component;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Component;
import za.co.flatrocksolutions.frscodesample.di.module.ApiModule;
import za.co.flatrocksolutions.frscodesample.di.module.ApplicationModule;
import za.co.flatrocksolutions.frscodesample.di.module.GsonModule;
import za.co.flatrocksolutions.frscodesample.di.module.InteractorModule;
import za.co.flatrocksolutions.frscodesample.di.module.OkHttpModule;
import za.co.flatrocksolutions.frscodesample.di.module.PicassoModule;
import za.co.flatrocksolutions.frscodesample.di.module.RetrofitModule;
import za.co.flatrocksolutions.frscodesample.di.module.RxModule;
import za.co.flatrocksolutions.frscodesample.di.qualifier.ApplicationContext;
import za.co.flatrocksolutions.frscodesample.di.scope.FRSApplicationScope;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.profile_list.adapter.UserProfileListAdapter;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/20/2017.
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
public interface ApplicationComponent {
    UserProfileInteractor exposeUserProfileInteractor();
    SchedulerProvider exposeSchedulerProvider();
    Picasso exposePicasso();
    @ApplicationContext Context exposeContext();

}
