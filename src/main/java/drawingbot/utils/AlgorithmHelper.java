package drawingbot.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class AlgorithmHelper {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // Algorithm was developed by Jack Elton Bresenham in 1962
    // https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
    // Traslated from pseudocode labled "Simplification" from the link above.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void bresenham(int x0, int y0, int x1, int y1, BiConsumer<Integer, Integer> func) {
        int sx, sy;
        int e2;

        int deltaX = Math.abs(x1-x0);
        int deltaY = Math.abs(y1-y0);
        if (x0 < x1) { sx = 1; } else { sx = -1; }
        if (y0 < y1) { sy = 1; } else { sy = -1; }
        int err = deltaX-deltaY;

        while (true) {
            func.accept(x0, y0);
            if ((x0 == x1) && (y0 == y1)) {
                return;
            }
            e2 = 2*err;
            if (e2 > -deltaY) {
                err = err - deltaY;
                x0 = x0 + sx;
            }
            if (e2 < deltaX) {
                err = err + deltaX;
                y0 = y0 + sy;
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // Midpoint circle algorithm
    // https://en.wikipedia.org/wiki/Midpoint_circle_algorithm
    // I had to create 8 arrays of points then append them, because normaly order is not important.
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList <Point> midpoint_circle(int x0, int y0, int radius) {
        ArrayList <Point> pnts = new ArrayList <>();

        ArrayList <Point> p1 = new ArrayList <>();
        ArrayList <Point> p2 = new ArrayList <>();
        ArrayList <Point> p3 = new ArrayList <>();
        ArrayList <Point> p4 = new ArrayList <>();
        ArrayList <Point> p5 = new ArrayList <>();
        ArrayList <Point> p6 = new ArrayList <>();
        ArrayList <Point> p7 = new ArrayList <>();
        ArrayList <Point> p8 = new ArrayList <>();

        int x = radius;
        int y = 0;
        int err = 0;

        while (x >= y) {
            p1.add(new Point(x0 + x, y0 + y));
            p2.add(new Point(x0 + y, y0 + x));
            p3.add(new Point(x0 - y, y0 + x));
            p4.add(new Point(x0 - x, y0 + y));
            p5.add(new Point(x0 - x, y0 - y));
            p6.add(new Point(x0 - y, y0 - x));
            p7.add(new Point(x0 + y, y0 - x));
            p8.add(new Point(x0 + x, y0 - y));

            if (err <= 0) {
                y += 1;
                err += 2*y + 1;
            }
            if (err > 0) {
                x -= 1;
                err -= 2*x + 1;
            }
        }

        pnts.addAll(p1);
        pnts.addAll(p2);
        pnts.addAll(p3);
        pnts.addAll(p4);
        pnts.addAll(p5);
        pnts.addAll(p6);
        pnts.addAll(p7);
        pnts.addAll(p8);

        return pnts;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

}
