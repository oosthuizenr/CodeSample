package za.co.flatrocksolutions.frscodesample.api;


import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;

/**
 * Created by renier on 4/20/2017.
 */

public interface IProfileApi {

    @GET("/user")
    Observable<ArrayList<UserProfile>> getUserProfiles();

    @GET("/user/{user_id}/about")
    Observable<String> getUserAbout(@Path("user_id") String userId);

    @GET("/user/{user_id}/interests")
    Observable<ArrayList<String>> getUserInterests(@Path("user_id") String userId);
}
