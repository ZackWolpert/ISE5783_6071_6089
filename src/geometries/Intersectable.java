package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 This interface defines the method for finding the intersections between an intersectable object and a ray.
 */
public abstract class Intersectable {

    /**

     * A static class GeoPoint represents a geometric point associated with a specific geometry.
     * It contains a reference to the geometry and the point itself.
     */
    public static class GeoPoint {
        /**
         * the geometry
         */
        public Geometry geometry;
        /**
         * A point on the geometry
         */
        public final Point point;

        /**
         * Constructor of GeoPoint using geometry and point
         * @param geometry the geometry
         * @param point a point on the geometry
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof GeoPoint other)
                return this.point.equals(((GeoPoint) obj).point) &&
                        this.geometry == ((GeoPoint) obj).geometry;
            return false;
        }

        @Override
        public String toString() {
            return "GeoPoint:" +
                    "geometry =" + geometry +
                    ", point =" + point;
        }
    }

    /**
     * Find the intersections between an intersectable object and a ray.
     * @param ray the ray to intersect with the object.
     * @return a List of Point objects representing the intersections.
     */
    public abstract List<Point> findIntersections(Ray ray);

    /**
     * Finds all intersection points and its geometries between the geometry and a ray
     * @param ray the ray to intersect with
     * @return list of all the intersection points with their geometries
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Finds all intersection points and its geometries between the geometry and a ray - helper function
     * @param ray the ray to intersect with
     * @return list of all the intersection points with their geometries
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){return null;}
}
