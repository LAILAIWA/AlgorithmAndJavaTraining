package categories.java.a7defaultmethod;

/**
 * 可移动和旋转
 */
public class Sun implements Moveable,Rotatable {
    @Override
    public int getRotationAngle() {
        return 0;
    }
    @Override
    public void setRotationAngle(int angleInDegress) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }
}
