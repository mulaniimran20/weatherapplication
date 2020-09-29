package com.synerzip.assignment.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.synerzip.assignment.R;

public class ImageBindAdapter {
    @BindingAdapter("wheatherImage")
    public static void loadImage(ImageView view, String imageUrl) {
        System.out.println("wheatherImageURL = "+imageUrl);
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

}
