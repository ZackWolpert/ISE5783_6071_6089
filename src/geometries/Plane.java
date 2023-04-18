package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
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
}
