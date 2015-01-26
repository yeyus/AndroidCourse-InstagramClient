package com.ea7jmf.instagramviewer;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ea7jmf.instagramviewer.models.InstagramPhoto;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by yeyus on 1/22/15.
 */
public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto> {

    private static final String GLOBE_EMOJI = "🌍";
    private static final String CLOCK_EMOJI = "🕜";
    private static final String HEART_EMOJI = "❤";

    TextView tvCaption;
    TextView txtUsername;
    TextView txtLocation;
    TextView txtPostingTime;
    TextView txtLikes;
    ImageView imgPhoto;
    ImageView imgAvatar;

    public InstagramPhotoAdapter(Context context, List<InstagramPhoto> photos) {
        super(context, android.R.layout.simple_list_item_1, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.item_photo, parent, false);
        }

        tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        txtUsername = (TextView) convertView.findViewById(R.id.txtUsername);
        txtLocation = (TextView) convertView.findViewById((R.id.txtLocation));
        txtPostingTime = (TextView) convertView.findViewById(R.id.txtPostingTime);
        txtLikes = (TextView) convertView.findViewById(R.id.txtLikes);
        imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
        imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);

        if (photo.getCaption() != null) {
            tvCaption.setText(photo.getCaption());
            tvCaption.setVisibility(View.VISIBLE);
        } else {
            tvCaption.setVisibility(View.INVISIBLE);
        }

        txtUsername.setText(photo.getUser().getUsername());

        // Writing location info or hiding
        if (photo.getLocationName() != null) {
            txtLocation.setText(GLOBE_EMOJI + " " + photo.getLocationName());
        } else if (photo.getLatitude() != 0.0 && photo.getLongitude() != 0.0) {
            DecimalFormat df = new DecimalFormat("#.00");
            txtLocation.setText(GLOBE_EMOJI + " " + df.format(photo.getLatitude()) + ", " + df.format(photo.getLongitude()));
            txtLocation.setVisibility(View.VISIBLE);
        } else {
            txtLocation.setVisibility(View.INVISIBLE);
        }

        // Set likes text
        txtLikes.setText(HEART_EMOJI + " " +
                convertView.getResources().getQuantityString(
                        R.plurals.photo_likes,
                        photo.getLikesCount(),
                        photo.getLikesCount()));

        // Set relative time text
        txtPostingTime.setText(CLOCK_EMOJI + " " + DateUtils.getRelativeTimeSpanString(photo.getCreatedTime() * 1000,
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS));

        // Set image height
        imgPhoto.getLayoutParams().height = photo.getImageHeight();
        // Reset image view
        imgPhoto.setImageResource(0);

        Picasso.with(getContext())
                .load(photo.getImageUrl())
                .placeholder(R.drawable.no_photo_grey_1x)
                .into(imgPhoto);
        Picasso.with(getContext()).load(photo.getUser().getProfilePicUrl()).into(imgAvatar);

        return convertView;
    }
}
