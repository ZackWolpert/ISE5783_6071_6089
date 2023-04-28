package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class Geometries implements  Intersectable{
    private List<Intersectable> geometriesList;

    public  Geometries()
    {
        this.geometriesList = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries){
        this.geometriesList = new LinkedList<>();
        add(geometries);
    }

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
            List<Point> geometryIntersectionPoints = geometry.findIntersections(ray);
            if (geometryIntersectionPoints != null) {
                if (result == null) { // first time with intersections - start new LinkedList. Then just add .
                    result = new LinkedList<>();
                }
                result.addAll(geometryIntersectionPoints);
            }
        }
        return result;
    }
}
