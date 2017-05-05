package de.ironcoding.fitsim.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import de.ironcoding.fitsim.logic.Body;

/**
 * Created by larsl on 05.05.2017.
 */

public class FormatUtil {

    public static DecimalFormat baseFloatForatter() {
        DecimalFormat decimalFormat = new DecimalFormat(Body.FLOAT_FORMAT_PATTERN);
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(symbols);
        return decimalFormat;
    }

}
