package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric object in a three-dimensional space.
 * It provides methods for getting the normal vector of the object.
 */
public interface Geometry extends Intersectable {
    /**
     * Returns the normal vector of the geometry object at the specified point.
     *
     * @param other the point on the geometry object.
     * @return the normal vector of the geometry object at the specified point.
     */
    Vector getNormal(Point other);
}
