package de.ironcoding.fitsim.persistance;

import android.content.Context;

/**
 * Created by larsl on 30.04.2017.
 */

public class FitSimOpenHelper extends DaoMaster.OpenHelper {

    private static final String DB_NAME = "fitsim";

    public FitSimOpenHelper(Context context) {
        super(context, DB_NAME);
    }


}
