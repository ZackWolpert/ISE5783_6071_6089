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
    public Vector getL(Point p);
}
