package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {

    /**
     * Retrieves the intensity of the light at a certain Point.
     * @param p point Il given
     * @return the intensity of the light at the point given
     */
    public Color getIntensity(Point p);

    /**
     * Gets vector from the given point to the light source
     * @param p the point
     * @return The vector
     */
    public Vector getL(Point p);

    /**
     * Calculates the distance between the light source and a point
     * @param point a point
     * @return the distance
     */
    double getDistance(Point point);

}
