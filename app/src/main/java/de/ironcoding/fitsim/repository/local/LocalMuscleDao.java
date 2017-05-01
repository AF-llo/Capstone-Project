package de.ironcoding.fitsim.repository.local;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.ironcoding.fitsim.logic.Muscle;
import de.ironcoding.fitsim.logic.Muscles;
import de.ironcoding.fitsim.persistance.json.JsonMuscle;
import de.ironcoding.fitsim.persistance.json.JsonMuscles;
import de.ironcoding.fitsim.repository.IMusclesDao;

/**
 * Created by larsl on 30.04.2017.
 */

public class LocalMuscleDao implements IMusclesDao {

    private static final String ASSETS_FILE_NAME_DE = "muscles_de.json";

    private static final String ASSETS_FILE_NAME_EN = "muscles_en.json";

    private AssetManager assetManager;

    private Locale locale;

    public LocalMuscleDao(AssetManager assetManager, Locale locale) {
        this.assetManager = assetManager;
        this.locale = locale;
    }

    @Override
    public Muscles load() {
        return Muscles.buildUpMuscles(readMusclesFromAssets());
    }

    private List<Muscle> readMusclesFromAssets() {
        String fileName = Locale.GERMANY.getCountry().equals(locale.getCountry()) ? ASSETS_FILE_NAME_DE : ASSETS_FILE_NAME_EN;
        List<Muscle> initialMuscles = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(fileName));
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            inputStreamReader.close();

            JsonMuscles muscles = new Gson().fromJson(builder.toString(), JsonMuscles.class);
            for (JsonMuscle muscle : muscles.getMuscles()) {
                initialMuscles.add(Muscle.build(muscle.getMuscleId(), muscle.getName(), muscle.getLimit()));
            }
        } catch (IOException e) {
            // TODO: 30.04.2017 log
        }
        return initialMuscles;
    }
}
