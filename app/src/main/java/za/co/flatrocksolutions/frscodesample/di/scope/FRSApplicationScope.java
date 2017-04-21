package za.co.flatrocksolutions.frscodesample.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by renier on 4/20/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FRSApplicationScope {
}
