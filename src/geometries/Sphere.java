package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Sphere class represents a sphere shape in three-dimensional space.
 * It is a subclass of the RadialGeometry class and adds a center property to represent the center of the sphere.
 */
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

    @Override
    public Vector getNormal(Point myPoint) {
        return null;
    }
}
