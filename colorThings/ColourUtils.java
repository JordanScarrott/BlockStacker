package colorThings;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jordan on 2016-12-01.
 */
public abstract class ColourUtils {

    /**
     * @return A random colorThings
     */
    public static Color randomColour() {
        Color colour = null;
        Random rand = new Random();
        int choice = rand.nextInt(6);

        switch (choice) {
            case 0:
                colour = new Color(148, 40, 30);
                break;
            case 1:
                colour = new Color(62, 151, 56);
                break;
            case 2:
                colour = new Color(44, 68, 147);
                break;
            case 3:
                colour = new Color(204, 120, 50);
                break;
            case 4:
                colour = new Color(207, 214, 57);
                break;
            case 5:
                // Purple
                colour = new Color(91, 0, 183);
                break;
            default:
                System.out.println("asdfasdfasdf");
        }

        assert colour != null;
        return colour;
    }

}
