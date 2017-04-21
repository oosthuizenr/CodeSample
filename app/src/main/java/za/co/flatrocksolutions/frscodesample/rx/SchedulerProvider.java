package za.co.flatrocksolutions.frscodesample.rx;

import io.reactivex.Scheduler;

/**
 * Created by renier on 4/21/2017.
 */

public interface SchedulerProvider {
    Scheduler ui();
    Scheduler computation();
    Scheduler io();
}
