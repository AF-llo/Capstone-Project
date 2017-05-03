package de.ironcoding.fitsim.util;

import de.ironcoding.fitsim.R;

/**
 * Created by larsl on 03.05.2017.
 */

public class IconUtil {

    public static final int ID_BENCHPRESS = 0;
    public static final int ID_PULLDOWNS = 1;
    public static final int ID_SCOTTCURLS = 2;
    public static final int ID_CRUNCHES = 3;
    public static final int ID_SQUADS = 4;
    public static final int ID_LEGPRESS = 5;
    public static final int ID_CYCLING = 6;
    public static final int ID_ROWING = 7;

    public static final int ID_CHICKEN_RICE_BROCCOLI = 0;
    public static final int ID_CEREALS_CURD = 1;
    public static final int ID_PROTEINE_SHAKE = 2;
    public static final int ID_COFFE = 3;
    public static final int ID_ENERGY_DRINK = 4;

    public static int activityResIdForIcon(int id) {
        switch (id) {
            case ID_BENCHPRESS:
                return R.drawable.ico_studio_bankdruecken;
            case ID_PULLDOWNS:
                return R.drawable.ico_studio_klimmzuege;
            case ID_SCOTTCURLS:
                return R.drawable.ico_studio_scottcurls;
            case ID_CRUNCHES:
                return R.drawable.ico_studio_crunches;
            case ID_SQUADS:
                return R.drawable.ico_studio_kniebeuge;
            case ID_LEGPRESS:
                return R.drawable.ico_studio_beinpresse;
            case ID_CYCLING:
                return R.drawable.ico_studio_radfahren;
            case ID_ROWING:
                return R.drawable.ico_studio_rudern;
        }
        return 0;
    }

    public static int nutritionResIdForIcon(int id) {
        switch (id) {
            case ID_CHICKEN_RICE_BROCCOLI:
                return R.drawable.ico_diet_chicken;
            case ID_CEREALS_CURD:
                return R.drawable.ico_diet_cereals;
            case ID_PROTEINE_SHAKE:
                return R.drawable.ico_diet_protein_shake;
            case ID_COFFE:
                return R.drawable.ico_diet_coffee;
            case ID_ENERGY_DRINK:
                return R.drawable.ico_diet_energy_drink;
        }
        return 0;
    }

}
