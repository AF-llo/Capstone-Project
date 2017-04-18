package de.ironcoding.fitsim.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by larsl on 18.04.2017.
 */

public class LevelListRepository<T extends ILevelItem> extends BaseLevelRepository<List<T>> {

    public LevelListRepository(IDao dao) {
        super(dao);
    }

    @Override
    protected List<T> loadByLevel(LevelSpecification levelSpecification) {
        List<T> specificList = new ArrayList<>();
        if (dao != null) {
            List<T> list = dao.load();
            for (T listItem : list) {
                if (levelSpecification.meetSpecification(listItem)) {
                    specificList.add(listItem);
                }
            }
        }
        return specificList;
    }
}
