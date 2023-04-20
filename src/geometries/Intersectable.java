package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 This interface defines the method for finding the intersections between an intersectable object and a ray.
 */
public interface Intersectable {
    /**

     Find the intersections between an intersectable object and a ray.
     @param ray the ray to intersect with the object.
     @return a List of Point objects representing the intersections.
     */
    List<Point> findIntsersections(Ray ray);
}
