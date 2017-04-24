package za.co.flatrocksolutions.frscodesample.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by renier on 4/20/2017.
 */

public class UserProfile {
    @SerializedName("Id")
    private String mId;

    @SerializedName("Name")
    private String mName;

    @SerializedName("ProfilePictureUrl")
    private String mProfilePictureUrl;

    @SerializedName("Title")
    private String mTitle;

    public String getId() {
        return mId;
    }
    public String getName() {
        return mName;
    }
    public String getProfilePictureUrl() {
        return mProfilePictureUrl;
    }
    public String getTitle() {
        return mTitle;
    }
}
