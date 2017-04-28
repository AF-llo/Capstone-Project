package de.ironcoding.fitsim.repository;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.ILevelItem;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Type;

/**
 * Created by larsl on 27.04.2017.
 */

public class Filter {

    public static <T extends ILevelItem> List<T> filterForLevel(List<T> items, Level level) {
        List<T> filteredItems = new ArrayList<>();
        for (T item : items) {
            if (level.getValue() >= item.getMinLevel()) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    public static <T extends ITypedItem> List<T> filterForType(List<T> items, Type type) {
        List<T> filteredItems = new ArrayList<>();
        for (T item : items) {
            if (item.getType().getId() == type.getId()) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

}
