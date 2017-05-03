package de.ironcoding.fitsim.ui.bindings;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by larsl on 03.05.2017.
 */

public class ImageViewBindings {
    @BindingAdapter("bind:resid")
    public static void setResId(ImageView imageView, int ressourceId) {
        Glide.with(imageView.getContext()).load(ressourceId).into(imageView);
    }
}
