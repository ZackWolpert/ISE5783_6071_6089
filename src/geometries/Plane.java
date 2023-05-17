package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry {
    private final Point p0;
    private final Vector normal;

    /**
     * Constructs a new Plane object with three reference points - one of them we choose as main reference point.
     *
     * @param p1  the first reference point - and the reference point we chose as the main one of the plane.
     * @param p2 the second reference point.
     * @param p3  the third reference point.
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = v1.crossProduct(v2).normalize();
        this.p0 = p1; // נקודות ייחוס למישור
    }

    /**
     * Constructs a new Plane object with a reference point and a normal vector.
     *
     * @param point  the reference point of the plane.
     * @param vector the normal vector of the plane.
     */
    public Plane(Point point, Vector vector) {
        this.normal = vector.normalize();
        this.p0 = point;
    }

    /**
     * Returns the reference point of the plane.
     *
     * @return the reference point of the plane.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector of the plane .
     */

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point other) {
        return normal;

    }

    /**
     * Find the intersections between a plane and a ray.
     *
     * @param ray the ray to intersect with the plane.
     * @return a List of GeoPoint objects representing the intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector rayDir = ray.getDir();
        Point rayP0 = ray.getP0();
        try{
            Vector v = p0.subtract(rayP0);
            double denominator = rayDir.dotProduct(normal);
            if(isZero(denominator)){
                return  null;
            }
            else {
                double t = alignZero(v.dotProduct(normal)/denominator);
                if(isZero(t) || t < 0){return null;}
                else{return  List.of(new GeoPoint(this,ray.getPoint(t)));}
            }
        }catch (IllegalArgumentException zeroVector) {
            return null;
        }
    }
}
