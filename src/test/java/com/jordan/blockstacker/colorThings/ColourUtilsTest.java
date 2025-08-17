package com.jordan.blockstacker.colorThings;

import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColourUtilsTest {

    @Test
    public void testRandomColour() {
        List<Color> expectedColors = Arrays.asList(
                new Color(148, 40, 30),
                new Color(62, 151, 56),
                new Color(44, 68, 147),
                new Color(204, 120, 50),
                new Color(207, 214, 57),
                new Color(91, 0, 183)
        );

        for (int i = 0; i < 100; i++) {
            Color randomColor = ColourUtils.randomColour();
            assertTrue(expectedColors.contains(randomColor), "Returned color is not one of the expected colors");
        }
    }
}
