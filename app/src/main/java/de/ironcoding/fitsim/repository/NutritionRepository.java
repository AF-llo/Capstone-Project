package de.ironcoding.fitsim.repository;

import java.util.List;

import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Nutrition;
import de.ironcoding.fitsim.logic.Type;

/**
 * Created by larsl on 27.04.2017.
 */

public class NutritionRepository extends BaseDaoRepository<List<Nutrition>, INutritionDao> {
    public NutritionRepository(INutritionDao nutritionDao) {
        super(nutritionDao);
    }

    public List<Nutrition> loadForLevel(Level level) {
        checkDao();
        return dao.loadForLevel(level);
    }

    public List<Nutrition> loadForTYpe(int typeId) {
        checkDao();
        return dao.loadForType(new Type(typeId, ""));
    }

    public List<Nutrition> loadForLevelAndType(Level level, int typeId) {
        checkDao();
        List<Nutrition> levelNutrition = loadForLevel(level);
        return Filter.filterForType(levelNutrition, new Type(typeId, ""));
    }
}
