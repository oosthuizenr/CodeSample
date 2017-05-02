package za.co.flatrocksolutions.frscodesample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by renier on 4/20/2017.
 */

public class UserProfile implements Parcelable {
    @SerializedName("Id")
    private String mId;

    @SerializedName("Name")
    private String mName;

    @SerializedName("ProfilePictureUrl")
    private String mProfilePictureUrl;

    @SerializedName("Title")
    private String mTitle;

    public UserProfile() {

    }

    public UserProfile(String mId, String mName, String mProfilePictureUrl, String mTitle) {
        this.mId = mId;
        this.mName = mName;
        this.mProfilePictureUrl = mProfilePictureUrl;
        this.mTitle = mTitle;
    }

    protected UserProfile(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mProfilePictureUrl = in.readString();
        mTitle = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mName);
        parcel.writeString(mProfilePictureUrl);
        parcel.writeString(mTitle);
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };


}
