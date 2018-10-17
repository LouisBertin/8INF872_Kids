package com.example.louisbertin.child.helpers;

import android.graphics.Color;

import java.util.Random;

/**
 * All String helper are here !
 */
public class StringHelper {

    /**
     * generate random character
     * @return
     */
    public static String generateRandomCharacter() {
        Random rnd = new Random();
        char c = (char) (rnd.nextInt(26) + 'a');
        c = Character.toUpperCase(c);

        return Character.toString(c);
    }

    /**
     * generate random color
     * @return
     */
    public static int generateRandomColor() {
        Random rnd = new Random();

        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
