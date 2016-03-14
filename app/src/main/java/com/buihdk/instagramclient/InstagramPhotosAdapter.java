package com.buihdk.instagramclient;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    // What data do we need from the activity
    // Context, Data Source

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    // What our item looks like
    // Use the template to display each photo

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstagramPhoto photo = getItem(position);
        // Check if we are using a recycled view, if not we need to inflate
        if (convertView == null) {
            // create a new view from template
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false); //parent: within the container; false: but we do not attach to the container yet
        }
        // Lookup the views for populating the data (username, likes count, photo, caption)
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvLikesCount = (TextView) convertView.findViewById(R.id.tvLikesCount);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);

        // Insert the model data into each of the items
        tvUsername.setText(photo.username);
        tvLikesCount.setText("" + NumberFormat.getIntegerInstance().format(photo.likesCount) + " likes");
        tvCaption.setText(photo.caption);
        // Clear out the ImageView if it was recycled (right away)
        ivPhoto.setImageResource(0);
        // Insert the image using picasso (send out async)
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);
        // Return the created item as a view
        return convertView;
    }
}
