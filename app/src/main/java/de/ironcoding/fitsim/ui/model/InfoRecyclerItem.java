package de.ironcoding.fitsim.ui.model;

import android.content.Context;
import android.databinding.Bindable;

import java.text.DecimalFormat;

import de.appsfactory.mvplib.presenter.MVPRecyclerItem;
import de.ironcoding.fitsim.BR;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Body;

/**
 * Created by larsl on 04.05.2017.
 */

public abstract class InfoRecyclerItem extends MVPRecyclerItem {

    protected DecimalFormat formatter;

    public InfoRecyclerItem() {
        formatter = new DecimalFormat(Body.FLOAT_FORMAT_PATTERN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_info_item;
    }

    @Override
    public int getItemId() {
        return BR.item;
    }

    @Bindable
    public String getTitle() {
        return "";
    }

    @Bindable
    public String getSubtitle() {
        return "";
    }

    public abstract String getFormattedTitle(Context context, String string);

    public abstract String getFormattedSubtitle(Context context, String string);
}
