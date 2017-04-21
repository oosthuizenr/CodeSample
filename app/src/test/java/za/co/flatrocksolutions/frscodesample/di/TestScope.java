package za.co.flatrocksolutions.frscodesample.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by renier on 4/21/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface TestScope {
}
