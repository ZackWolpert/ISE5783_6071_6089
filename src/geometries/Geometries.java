package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class stores the group of geometrical shapes .
 *
 */
public class Geometries extends Intersectable{
    private List<Intersectable> geometries;

    /**
     * geometries default constructor - new geometriesList
     */
    public Geometries()
    {
        this.geometries = new LinkedList<>();
    }

    /**
     * Constructor using geometries
     *
     * @param geometries a group of geometrical shapes
     */
    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();
        add(geometries);
    }

    /**
     * A function which adds an object to the intersectable collection
     *
     * @param geometries a group of geometrical shapes
     */
    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries));
    }
    /**
     * Find the intersections between intersectable objects and a ray.
     *
     * @param ray the ray to intersect with the objects.
     * @return a List of GeoPoint objects representing the intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null; // starts at null - if no intersection points .
        for (Intersectable geometry : geometries) {
            List<GeoPoint> intersections = geometry.findGeoIntersectionsHelper(ray);
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
