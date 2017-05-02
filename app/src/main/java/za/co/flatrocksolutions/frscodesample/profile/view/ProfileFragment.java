package za.co.flatrocksolutions.frscodesample.profile.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private static final String USER_PROFILE_KEY = "UserProfile";

    public static ProfileFragment newInstance(UserProfile profile) {
        ProfileFragment fragment = new ProfileFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable(USER_PROFILE_KEY, profile);

        fragment.setArguments(arguments);

        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserProfile profile = getArguments().getParcelable(USER_PROFILE_KEY);

        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        ((TextView) v.findViewById(R.id.tvTest)).setText(profile.getName());

        return v;
    }

}
