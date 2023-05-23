package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 * The Triangle class represents a triangle in three-dimensional space.
 * It is a subclass of the Polygon class and is defined by three points.
 */

public class Triangle extends Polygon {
    /**
     * Constructs a Triangle object with the specified points.
     *
     * @param p1  the first point of the triangle.
     * @param p2 the second point of the triangle.
     * @param p3  the third point of the triangle .
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Find the intersections between a triangle and a ray.
     *
     * @param ray the ray to intersect with the triangle.
     * @return a List of GeoPoint objects representing the intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        // find intersections with plane of triangle
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);
        List<GeoPoint> geoPoints = plane.findGeoIntersectionsHelper(ray,maxDistance); // color yet to be implemented
        if (geoPoints == null)
            return null;
        // check if they're in the triangle
        Point rayP0 = ray.getP0();
        Vector rayDir = ray.getDir();
        Vector v1 = (p1.subtract(rayP0));
        Vector v2 = (p2.subtract(rayP0));
        Vector v3 = (p3.subtract(rayP0));
        double vn1 = alignZero(rayDir.dotProduct((v1.crossProduct(v2)).normalize()));
        if (isZero(vn1)) return null;
        double vn2 = alignZero(rayDir.dotProduct((v2.crossProduct(v3)).normalize()));
        if (vn1 * vn2 <= 0) return null;
        double vn3 = alignZero(rayDir.dotProduct((v3.crossProduct(v1)).normalize()));
        if (vn1 * vn3 <= 0) return null;
        List<GeoPoint> geoPointsRet = new LinkedList<>();
        for (GeoPoint geoPoint : geoPoints){
            geoPointsRet.add(new GeoPoint(this,geoPoint.point)); // need to implement this to add emission color .
        }
        return geoPointsRet;
    }
}
