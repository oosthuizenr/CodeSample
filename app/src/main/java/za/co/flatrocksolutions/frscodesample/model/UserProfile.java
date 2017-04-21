package za.co.flatrocksolutions.frscodesample.model;

import java.util.ArrayList;

/**
 * Created by renier on 4/20/2017.
 */

public class UserProfile {
    private String mId;
    private String mName;
    private String mProfilePictureUrl;
    private String mAbout;
    private ArrayList<String> mInterests;

    public String getId() {
        return mId;
    }
    public String getName() {
        return mName;
    }
    public String getProfilePictureUrl() {
        return mProfilePictureUrl;
    }

    public String getAbout() {
        return mAbout;
    }

    public void setAbout(String mAbout) {
        this.mAbout = mAbout;
    }

    public ArrayList<String> getInterests() {
        return mInterests;
    }

    public void setInterests(ArrayList<String> mInterests) {
        this.mInterests = mInterests;
    }


}
