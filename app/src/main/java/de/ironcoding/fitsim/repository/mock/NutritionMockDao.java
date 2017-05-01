package de.ironcoding.fitsim.repository.mock;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.EnergyBooster;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Meal;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.logic.Type;
import de.ironcoding.fitsim.repository.Filter;
import de.ironcoding.fitsim.repository.INutritionDao;

/**
 * Created by larsl on 18.04.2017.
 */

public class NutritionMockDao implements INutritionDao {

    @Override
    public List<Nutrition> load() {
        List<Nutrition> mockedNutrition = new ArrayList<>();
        mockedNutrition.add(new Meal("Proteineshake", 20, 5, 3, 1, 1));
        mockedNutrition.add(new Meal("Spaghetti", 20, 60, 15, 1, 4));
        mockedNutrition.add(new Meal("Cereals", 30, 50, 10, 1, 3));
        mockedNutrition.add(new EnergyBooster("Coffee", 100, 1, 1));
        mockedNutrition.add(new EnergyBooster("Energydrink", 100, 1, 0));
        return mockedNutrition;
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
