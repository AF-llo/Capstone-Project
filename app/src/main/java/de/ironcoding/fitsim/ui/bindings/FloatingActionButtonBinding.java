package de.ironcoding.fitsim.ui.bindings;

import android.databinding.BindingAdapter;
import android.support.design.widget.FloatingActionButton;

/**
 * Created by larsl on 05.05.2017.
 */

public class FloatingActionButtonBinding {

    @BindingAdapter("app:iconresid")
    public static void setFabIcon(FloatingActionButton floatingActionButton, int ressourceId) {
        floatingActionButton.setImageResource(ressourceId);
    }

}
