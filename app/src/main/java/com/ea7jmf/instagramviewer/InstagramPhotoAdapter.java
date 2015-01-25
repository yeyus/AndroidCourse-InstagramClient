package com.ea7jmf.instagramviewer;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeyus on 1/22/15.
 */
public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto> {

    TextView tvCaption;
    TextView txtUsername;
    TextView txtLocation;
    TextView txtPostingTime;
    ImageView imgPhoto;

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
        imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);

        tvCaption.setText(photo.getCaption());
        txtUsername.setText(photo.getUsername());

        // If there's any location information just show to the user
        txtLocation.setVisibility(View.INVISIBLE);
        if (photo.getLatitude() != 0.0 && photo.getLongitude() != 0.0) {
            DecimalFormat df = new DecimalFormat("#.00");
            txtLocation.setText("⚑ " + df.format(photo.getLatitude()) + ", " + df.format(photo.getLongitude()));
            txtLocation.setVisibility(View.VISIBLE);
        }

        txtPostingTime.setText("🕛 " + DateUtils.getRelativeTimeSpanString(photo.getCreatedTime() * 1000,
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS));

        imgPhoto.getLayoutParams().height = photo.getImageHeight();
        // Reset image view
        imgPhoto.setImageResource(0);

        Picasso.with(getContext()).load(photo.getImageUrl()).into(imgPhoto);

        return convertView;
    }
}
