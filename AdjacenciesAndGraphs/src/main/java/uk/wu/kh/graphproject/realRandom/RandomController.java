/*
 * The MIT License
 *
 * Copyright 2024 kai.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.wu.kh.graphproject.realRandom;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.wu.kh.graphproject.JavaFXSimpleDialog;

/**
 * Calls the RealRandomLoader getIntArr two times and stores the integer arrays
 * for use in the ACO.
 *
 * @see RealRandomLoader
 * @author kai
 */
public class RandomController {

    private static int[] two;
    private static int[] three;
    public static boolean inited = false;

    public static void init() {
        try {
            //minimum, maximum, amount of requested numbers from random.org
            three = RealRandomLoader.getIntArr(0, 2, 10000);
            two = RealRandomLoader.getIntArr(0, 1, 5000);
        } catch (IOException ex) {
            JavaFXSimpleDialog.show("Error", "java.net.SocketException\nNetwork is unreachable");
        } catch (Exception ex) {
            Logger.getLogger(RandomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Loaded random int array sizes: " + two.length + ", " + three.length);
    }

    public static int[] getTwo() {
        return two;
    }

    public static int[] getThree() {
        return three;
    }
}
