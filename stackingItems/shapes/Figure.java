package shapes;

import java.awt.Shape;

/**
 * Abstract base class for drawable figures on the Canvas.
 * It groups common state and behavior such as position, color,
 * visibility and movement.
 */
public abstract class Figure {
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean visible;

    /**
     * Create a new figure at default position with default color.
     */
    public Figure() {
        xPosition = 0;
        yPosition = 0;
        color = "magenta";
        visible = false;
    }

    /**
     * Make this figure visible.
     */
    public void makeVisible() {
        visible = true;
        draw();
    }

    /**
     * Make this figure invisible.
     */
    public void makeInvisible() {
        erase();
        visible = false;
    }

    /**
     * Move the figure horizontally.
     * @param distance desired horizontal distance in pixels
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the figure vertically.
     * @param distance desired vertical distance in pixels
     */
    public void moveVertical(int distance) {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the figure horizontally.
     * @param distance desired horizontal distance in pixels
     */
    public void slowMoveHorizontal(int distance) {
        int delta = (distance < 0) ? -1 : 1;
        int steps = Math.abs(distance);

        for (int i = 0; i < steps; i++) {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the figure vertically.
     * @param distance desired vertical distance in pixels
     */
    public void slowMoveVertical(int distance) {
        int delta = (distance < 0) ? -1 : 1;
        int steps = Math.abs(distance);

        for (int i = 0; i < steps; i++) {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the color.
     * @param newColor the new color
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
    }

    /**
     * Build the AWT shape that represents this figure.
     * Each subclass must define how it is drawn.
     * @return the AWT shape of this figure
     */
    protected abstract Shape createShape();

    /**
     * Draw this figure on screen.
     */
    protected void draw() {
        if (visible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, createShape());
            canvas.wait(10);
        }
    }

    /**
     * Erase this figure from screen.
     */
    protected void erase() {
        if (visible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Return current x position.
     */
    protected int getXPosition() {
        return xPosition;
    }

    /**
     * Return current y position.
     */
    protected int getYPosition() {
        return yPosition;
    }

    /**
     * Return current color.
     */
    protected String getColor() {
        return color;
    }

    /**
     * Return visibility state.
     */
    protected boolean isVisible() {
        return visible;
    }
}