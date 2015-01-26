package com.ea7jmf.instagramviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ea7jmf.instagramviewer.models.InstagramComment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yeyus on 1/25/15.
 */
public class InstagramCommentsAdapter extends ArrayAdapter<InstagramComment> {

    private TextView txtComment;
    private TextView txtUsername;
    private ImageView imgCommentAvatar;

    public InstagramCommentsAdapter(Context context, List<InstagramComment> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramComment comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.item_comment, parent, false);
        }

        txtComment = (TextView) convertView.findViewById(R.id.txtComment);
        txtUsername = (TextView) convertView.findViewById(R.id.txtUsername);
        imgCommentAvatar = (ImageView) convertView.findViewById(R.id.imgCommentAvatar);

        txtComment.setText(comment.getText());
        txtUsername.setText(comment.getUser().getUsername());

        Picasso.with(getContext())
                .load(comment.getUser().getProfilePicUrl())
                .placeholder(R.drawable.no_photo_grey_1x)
                .into(imgCommentAvatar);

        return convertView;
    }
}
