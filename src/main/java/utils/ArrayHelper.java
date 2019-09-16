package utils;

/**
 * ArrayHelper - класс помошник в работе с массивами
 *
 * @version 1.0.1
 * @package logger
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class ArrayHelper {

    /**
     * copyPartArray - получить часть массива
     *
     * @access public
     * @param  a -
     * @param  start - индекс массива начиная с
     *               которого отрезается часть исходного массива
     * @return String[]
     */
    public static String[] copyPartArray ( String[] a, int start ) {
        if ( a == null ) {
            return null;
        }
        if (start > a.length) {
            return null;
        }
        String [] r = new String[a.length - start];
        System.arraycopy(a, start, r, 0, a.length - start);
        return r;
    }
}
