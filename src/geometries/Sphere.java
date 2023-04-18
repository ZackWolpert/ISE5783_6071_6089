package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Sphere class represents a sphere shape in three-dimensional space.
 * It is a subclass of the RadialGeometry class and adds a center property to represent the center of the sphere.
 * important L sphere equation : (x-firstCoordinate)^2 + (y-secondCoordinate)^2 + (z-thirdCoordinate)^2 = radius, while (firstCoordinate,secondCoordinate,thirdCoordinate) represents the center of the sphere.
 * */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a Sphere object with the specified radius and center.
     *
     * @param radius the radius of the sphere.
     * @param center the center of the sphere .
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * returns the center of the Sphere
     *
     * @return the center of the Sphere
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point other) {
        // assuming the point is on the surface od the sphere
        Point myZeroPoint = new Point(0,0,0); // need to use this to get myPoint as a vector, in order to calculate unit normal vector of sphere
        return other.subtract(myZeroPoint).scale(2).normalize();

    }
}
