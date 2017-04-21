package za.co.flatrocksolutions.frscodesample;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import za.co.flatrocksolutions.frscodesample.rx.SchedulerProvider;

/**
 * Created by renier on 4/21/2017.
 */

public class TestSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }
}
