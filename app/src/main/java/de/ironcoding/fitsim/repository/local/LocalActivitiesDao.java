package de.ironcoding.fitsim.repository.local;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Cardio;
import de.ironcoding.fitsim.logic.Exercise;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Type;
import de.ironcoding.fitsim.persistance.json.JsonActivities;
import de.ironcoding.fitsim.persistance.json.JsonActivity;
import de.ironcoding.fitsim.repository.Filter;
import de.ironcoding.fitsim.repository.IActivitiesDao;

/**
 * Created by larsl on 01.05.2017.
 */

public class LocalActivitiesDao implements IActivitiesDao {

    private static final String ASSETS_FILE_NAME_DE = "activities_de.json";

    private static final String ASSETS_FILE_NAME_EN = "activities_en.json";

    private AssetManager assetManager;

    private Locale locale;

    public LocalActivitiesDao(AssetManager assetManager, Locale locale) {
        this.assetManager = assetManager;
        this.locale = locale;
    }

    @Override
    public List<Activity> load() {
        return readActivitiesFromAssets();
    }

    private List<Activity> readActivitiesFromAssets() {
        String fileName = Locale.GERMANY.getLanguage().equals(locale.getLanguage()) ? ASSETS_FILE_NAME_DE : ASSETS_FILE_NAME_EN;
        List<Activity> localActivities = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(fileName));
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            inputStreamReader.close();

            JsonActivities activities = new Gson().fromJson(builder.toString(), JsonActivities.class);
            for (JsonActivity jsonActivity : activities.getActivities()) {
                Activity activity = createByType(jsonActivity);
                if (activity != null) {
                    localActivities.add(activity);
                }
            }
        } catch (IOException e) {
            // TODO: 30.04.2017 log
        }
        return localActivities;
    }

    private Activity createByType(JsonActivity jsonActivity) {
        int typeId = jsonActivity.getTypeId();
        Activity activity = null;
        if (typeId == Exercise.TYPE_INT) {
            activity = new Exercise(
                    jsonActivity.getName(),
                    jsonActivity.getPal(),
                    jsonActivity.getEffort(),
                    jsonActivity.getExperience(),
                    jsonActivity.getMuscleId(),
                    jsonActivity.getMinLevel());
        } else if (typeId == Cardio.TYPE_INT) {
            activity = new Cardio(
                    jsonActivity.getName(),
                    jsonActivity.getPal(),
                    jsonActivity.getEffort(),
                    jsonActivity.getExperience(),
                    jsonActivity.getMinLevel());
        }
        return activity;
    }

    @Override
    public List<Activity> loadForLevel(Level level) {
        return Filter.filterForLevel(load(), level);
    }

    @Override
    public List<Activity> loadForType(Type type) {
        return Filter.filterForType(load(), type);
    }
}
