package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The DirectionalLight class represents a directional light source .
 * It extends the Light class and implements the LightSource interface.
 */
public class DirectionalLight extends Light implements LightSource{

    private Vector direction;
    /**
     * Constructs a DirectionalLight object with the specified intensity and direction.
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    protected DirectionalLight(Color intensity,Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Retrieves the intensity of the light at a specified point.
     * @param p the point at which to evaluate the light intensity
     * @return the intensity of the light at the specified point
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * Retrieves the direction vector from the light source to a specified point.
     * @param p the point at which to evaluate the direction vector
     * @return the direction vector from the light source to the specified point
     */
    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }
}
