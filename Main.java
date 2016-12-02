import core.MyVector;

import javax.swing.*;

/**
 * Created by Jordan on 2016-11-29.
 */
public class Main {

    public static void main(String[] args) {
        Display display = new Display(650, 650);
        display.setSize(650, 650);
        display.setResizable(false);
        display.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        display.setLocationRelativeTo(null);
        display.setVisible(true);


/*        MyVector blockLocation = new MyVector(1, 2);
        MyVector shapeLocation = new MyVector(1, 1);
        MyVector temp;
        for(int i = 0; i < 4; i++) {
            temp = blockLocation.sub(shapeLocation);
            temp.rotate90();
            System.out.println("temp: " + temp);

            blockLocation.set(MyVector.add(temp, shapeLocation));
            System.out.println("blockLocation: " + blockLocation);

            System.out.println();
        }*/

    }

}
