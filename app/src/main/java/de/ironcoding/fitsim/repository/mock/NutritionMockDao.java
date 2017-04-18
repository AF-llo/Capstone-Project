package de.ironcoding.fitsim.repository.mock;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.EnergyBooster;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.repository.IDao;

/**
 * Created by larsl on 18.04.2017.
 */

public class NutritionMockDao implements IDao<List<Nutrition>> {

    @Override
    public List<Nutrition> load() {
        List<Nutrition> mockedNutrition = new ArrayList<>();
        mockedNutrition.add(new Nutrition("Proteineshake", 20, 5, 3, 1));
        mockedNutrition.add(new Nutrition("Spaghetti", 20, 60, 15, 1));
        mockedNutrition.add(new Nutrition("Cereals", 30, 50, 10, 1));
        mockedNutrition.add(new EnergyBooster("Coffee", 10, 1));
        mockedNutrition.add(new EnergyBooster("Energydrink", 10, 5));
        return mockedNutrition;
    }
}
