package de.ironcoding.fitsim.repository.local;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.ironcoding.fitsim.logic.EnergyBooster;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Meal;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.logic.Supplement;
import de.ironcoding.fitsim.logic.Type;
import de.ironcoding.fitsim.persistance.json.JsonNutrition;
import de.ironcoding.fitsim.persistance.json.JsonNutritions;
import de.ironcoding.fitsim.repository.Filter;
import de.ironcoding.fitsim.repository.INutritionDao;

/**
 * Created by larsl on 01.05.2017.
 */

public class LocalNutritionDao implements INutritionDao {
    private static final String ASSETS_FILE_NAME_DE = "nutrition_de.json";

    private static final String ASSETS_FILE_NAME_EN = "nutrition_en.json";

    private AssetManager assetManager;

    private Locale locale;

    public LocalNutritionDao(AssetManager assetManager, Locale locale) {
        this.assetManager = assetManager;
        this.locale = locale;
    }

    @Override
    public List<Nutrition> load() {
        return readNutritionFromAssets();
    }

    private List<Nutrition> readNutritionFromAssets() {
        String fileName = Locale.GERMANY.getLanguage().equals(locale.getLanguage()) ? ASSETS_FILE_NAME_DE : ASSETS_FILE_NAME_EN;
        List<Nutrition> localNutritions = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(fileName));
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            inputStreamReader.close();

            JsonNutritions nutritions = new Gson().fromJson(builder.toString(), JsonNutritions.class);
            for (JsonNutrition jsonNutrition : nutritions.getNutritions()) {
                Nutrition nutrition = createByType(jsonNutrition);
                if (nutrition != null) {
                    localNutritions.add(nutrition);
                }
            }
        } catch (IOException e) {
            // TODO: 30.04.2017 log
        }
        return localNutritions;
    }

    private Nutrition createByType(JsonNutrition jsonNutrition) {
        int typeId = jsonNutrition.getTypeid();
        Nutrition nutrition = null;
        if (typeId == Meal.TYPE_INT) {
            nutrition = new Meal(
                    jsonNutrition.getName(),
                    jsonNutrition.getProteine(),
                    jsonNutrition.getCarbs(),
                    jsonNutrition.getFat(),
                    jsonNutrition.getLevel(),
                    jsonNutrition.getSaturationDuration());
        } else if (typeId == Supplement.TYPE_INT) {
            nutrition = new Supplement(
                    jsonNutrition.getName(),
                    jsonNutrition.getProteine(),
                    jsonNutrition.getCarbs(),
                    jsonNutrition.getFat(),
                    jsonNutrition.getLevel(),
                    jsonNutrition.getSaturationDuration());
        } else if (typeId == EnergyBooster.TYPE_INT) {
            nutrition = new EnergyBooster(
                    jsonNutrition.getName(),
                    jsonNutrition.getEnergy(),
                    jsonNutrition.getLevel(),
                    jsonNutrition.getSaturationDuration());
        }
        return nutrition;
    }

    @Override
    public List<Nutrition> loadForLevel(Level level) {
        return Filter.filterForLevel(load(), level);
    }

    @Override
    public List<Nutrition> loadForType(Type type) {
        return Filter.filterForType(load(), type);
    }
}
