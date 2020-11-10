package java.base.a7stream.test;

import java.util.Comparator;

public class Point {
    private final int x;
    private final int y;

    public final static Comparator<Point> compareByXAndThenY = Comparator.comparing(Point::getX).thenComparing(Point::getY);

    public Point moveRightBy(int x){
        return new Point(this.x + x, y);
    }


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
