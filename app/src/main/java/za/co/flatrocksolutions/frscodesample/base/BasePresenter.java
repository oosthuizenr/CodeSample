package za.co.flatrocksolutions.frscodesample.base;

/**
 * Created by renier on 4/20/2017.
 */

public interface BasePresenter<T> {
    void setView(T view);
    void onSubscribe();
    void onUnsubscribe();
}
