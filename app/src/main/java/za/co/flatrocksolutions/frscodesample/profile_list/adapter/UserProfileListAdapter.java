package za.co.flatrocksolutions.frscodesample.profile_list.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.flatrocksolutions.frscodesample.FRSApplication;
import za.co.flatrocksolutions.frscodesample.R;
import za.co.flatrocksolutions.frscodesample.di.DIHelper;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;

/**
 * Created by renier on 4/24/2017.
 */

public class UserProfileListAdapter extends RecyclerView.Adapter<UserProfileListAdapter.UserProfileListViewHolder>{
    private ArrayList<UserProfile> mData;

    public void setData(ArrayList<UserProfile> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public UserProfileListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserProfileListViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_profile_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(UserProfileListViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class UserProfileListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgProfile)
        ImageView imgProfile;

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @Inject
        Picasso mPicasso;


        public UserProfileListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            DIHelper.getAppComponent(itemView.getContext()).inject(this);
        }

        public void bind(UserProfile profile) {
            mPicasso
                    .load(profile.getProfilePictureUrl())
                    .noFade()
                    .into(imgProfile);

            tvName.setText(profile.getName());
            tvTitle.setText(profile.getTitle());
        }
    }


}
