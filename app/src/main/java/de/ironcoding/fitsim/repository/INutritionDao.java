package de.ironcoding.fitsim.repository;

import java.util.List;

import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.logic.Type;

/**
 * Created by larsl on 27.04.2017.
 */

public interface INutritionDao extends IDao<List<Nutrition>> {
    List<Nutrition> loadForLevel(Level level);
    List<Nutrition> loadForType(Type type);
}
