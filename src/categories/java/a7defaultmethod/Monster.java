package categories.java.a7defaultmethod;

/**
 * 可移动、旋转和缩放
 */
public class Monster implements Rotatable,Moveable,Resizeable{
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

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public void setHeight(int height) {

    }

    @Override
    public void setAbsoluteSize(int width, int height) {

    }


}
