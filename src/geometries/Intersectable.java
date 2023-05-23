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
     * Finds all intersection points and its geometries between the geometry and a ray
     *
     * @param ray the ray to intersect with
     * @return list of all the intersection points with their geometries
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * finds intersections between a shape and a ray where distance to ray head is smaller than max distance
     * @param ray the ray to intersect with
     * @param maxDistance maximum distance to ray head for returned points.
     * @return list of intersection points with corresponding geometries
     */

    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Finds all intersection points and its geometries between the geometry and a ray - helper function
     * @param ray the ray to intersect with
     * @return list of all the intersection points with their geometries
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * Find the intersections between an intersectable object and a ray.
     * @param ray the ray to intersect with the object.
     * @return a List of Point objects representing the intersections.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

}
