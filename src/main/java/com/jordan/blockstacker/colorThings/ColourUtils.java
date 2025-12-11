package com.jordan.blockstacker.colorThings;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jordan on 2016-12-01.
 */
public abstract class ColourUtils {

    private static final Random RANDOM = new Random();
    // Cache colors to avoid unnecessary object creation
    private static final Color[] COLORS = new Color[]{
            new Color(148, 40, 30),
            new Color(62, 151, 56),
            new Color(44, 68, 147),
            new Color(204, 120, 50),
            new Color(207, 214, 57),
            new Color(91, 0, 183) // Purple
    };

    /**
     * @return A random colorThings
     */
    public static Color randomColour() {
        return COLORS[RANDOM.nextInt(COLORS.length)];
    }

}
