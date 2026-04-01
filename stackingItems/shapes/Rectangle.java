package shapes;

import java.awt.Shape;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 *
 * @author Michael Kolling and David J. Barnes (Modified)
 * @version 2.0
 */
public class Rectangle extends Figure {

    public static final int EDGES = 4;

    private int height;
    private int width;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle() {
        super();
        height = 30;
        width = 30;
    }

    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown() {
        moveVertical(20);
    }

    /**
     * Change the size to the new size.
     * @param newHeight the new height in pixels
     * @param newWidth the new width in pixels
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /**
     * Create the AWT shape that represents this rectangle.
     * @return a java.awt.Rectangle with the current bounds
     */
    @Override
    protected Shape createShape() {
        return new java.awt.Rectangle(
            getXPosition(),
            getYPosition(),
            width,
            height
        );
    }
}
