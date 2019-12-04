package categories.java.a7defaultmethod;

/**
 * 可旋转
 */
public interface Rotatable {
    void setRotationAngle(int angleInDegress);
    int getRotationAngle();

    default void rotateBy(int angleInDegress){
        setRotationAngle((getRotationAngle() + angleInDegress) % 360);
    }
}
