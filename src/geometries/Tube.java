package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * The Tube class represents a tube shape in three-dimensional space.
 * It is a subclass of the RadialGeometry class and adds an axis ray property to represent the axis of the tube.
 */
public class Tube extends RadialGeometry {
    /**
     * The axis ray of the tube.
     */
    protected final Ray axisRay;

    /**
     * Constructs a Tube object with the specified radius and axis ray.
     *
     * @param radius  the radius of the tube.
     * @param axisRay the axis ray of the tube .
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * returns the ray of the tube
     *
     * @return the ray of the tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * returns the radius of the tube
     *
     * @return the radius of the tube
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point myPoint) {
        return  null;
    }

}
