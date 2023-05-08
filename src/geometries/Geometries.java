package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

/**
 * Geometries class stores the group of geometrical shapes .
 *
 */
public class Geometries implements Intersectable{
    private List<Intersectable> geometriesList;

    /**
     * geometries default constructor - new geometriesList
     */
    public Geometries()
    {
        this.geometriesList = new LinkedList<>();
    }

    /**
     * Constructor using geometries
     *
     * @param geometries a group of geometrical shapes
     */
    public Geometries(Intersectable... geometries){
        this.geometriesList = new LinkedList<>();
        add(geometries);
    }

    /**
     * A function which adds an object to the intersectable collection
     *
     * @param geometries a group of geometrical shapes
     */
    public void add(Intersectable... geometries){
        geometriesList.addAll(Arrays.asList(geometries));
    }
    /**
     * Find the intersections between intersectable objects and a ray.
     *
     * @param ray the ray to intersect with the objects.
     * @return a List of Point objects representing the intersections.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null; // starts at null - if no intersection points .
        for (Intersectable geometry : geometriesList) {
            List<Point> intersections = geometry.findIntersections(ray);
            if (intersections != null) {
                if (result == null) { // first time with intersections - start new LinkedList. Then just add .
                    result = new LinkedList<>();
                }
                result.addAll(intersections);
            }
        }
        return result;
    }
}
