package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The PointLight class represents a point light source
 * It extends the Light class and implements the LightSource interface.
 */
public class PointLight extends Light implements LightSource{

    private Point position;
    private double kC = 1,kL = 0,kQ = 0;
    /**
     * Constructs a PointLight object with the specified intensity and position.
     * @param intensity the intensity of the light
     * @param position the position of the light source
     */
    protected PointLight(Color intensity,Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation factor for the light.
     * @param kC the constant attenuation factor
     * @return the updated PointLight object
     */
    public PointLight setKc(double kC) {this.kC = kC;return this;}

    /**
     * Sets the linear attenuation factor for the light.
     * @param kL the linear attenuation factor
     * @return the updated PointLight object
     */
    public PointLight setKl(double kL) {this.kL = kL;return this;}

    /**
     * Sets the quadratic attenuation factor for the light.
     * @param kQ the quadratic attenuation factor
     * @return the updated PointLight object
     */
    public PointLight setKq(double kQ) {this.kQ = kQ;return this;}


    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        return getIntensity().scale(1/(kC+d*kL+d*d*kQ));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Calculates the distance between the light source and a point
     *
     * @param point a point
     * @return the distance
     */
    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}
