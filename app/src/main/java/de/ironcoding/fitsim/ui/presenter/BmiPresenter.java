package de.ironcoding.fitsim.ui.presenter;

import android.content.Context;
import android.databinding.Observable;

import java.text.DecimalFormat;

import de.appsfactory.mvplib.annotations.MVPIncludeToState;
import de.appsfactory.mvplib.util.ObservableString;
import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.Calories;
import de.ironcoding.fitsim.util.FormatUtil;

/**
 * Created by larsl on 05.05.2017.
 */

public class BmiPresenter extends BasePresenter {

    @MVPIncludeToState
    public ObservableString weight = new ObservableString("0");

    @MVPIncludeToState
    public ObservableString size = new ObservableString("0");

    @MVPIncludeToState
    public ObservableString bmi = new ObservableString();

    @MVPIncludeToState
    public ObservableString info = new ObservableString();

    private DecimalFormat floatFormat;

    private OnPropertyChangedCallback onPropertyChangedCallback = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i) {
            Context context = getContext();
            if (context == null) {
                return;
            }
            float weight = 0;
            float size = 0;
            try {
                weight = Float.valueOf(BmiPresenter.this.weight.get());
                size = Float.valueOf(BmiPresenter.this.size.get());
            } catch (NumberFormatException nfe) {
                info.set(context.getString(R.string.invalid_data));
            }
            info.set("");
            bmi.set(String.format(context.getString(R.string.bmi_result), formattedFloat(Calories.getBmi(weight, size))));
        }
    };

    @Override
    protected void onPresenterCreated() {
        super.onPresenterCreated();
        floatFormat = FormatUtil.baseFloatForatter();
        addPropertyCallback();
    }

    private void addPropertyCallback() {
        weight.addOnPropertyChangedCallback(onPropertyChangedCallback);
        size.addOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    private void clearPropertyCallback() {
        weight.removeOnPropertyChangedCallback(onPropertyChangedCallback);
        size.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    @Override
    protected void onAthleteLoaded() {
        super.onAthleteLoaded();
        if (!isRestored()) {
            Athlete athlete = getAthlete();
            Body.Stats stats = athlete.getBody().getStats();
            Body.Properties properties = athlete.getBody().getProperties();
            weight.set(formattedFloat(stats.getWeight()));
            size.set(formattedFloat(properties.getSize()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearPropertyCallback();
    }

    private String formattedFloat(float value) {
        return String.valueOf(floatFormat.format(value));
    }
}
