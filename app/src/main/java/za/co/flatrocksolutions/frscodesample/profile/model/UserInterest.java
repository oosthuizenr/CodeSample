package za.co.flatrocksolutions.frscodesample.profile.model;


import com.plumillonforge.android.chipview.Chip;

/**
 * Created by renier on 5/9/2017.
 */

public class UserInterest implements Chip {
    private String interest;

    public UserInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String getText() {
        return interest;
    }
}
