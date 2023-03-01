package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    private final Point p0;
    private final Vector normal;

    /**
     * Constructs a new Plane object with three reference points - one of them we choose as main reference point.
     *
     * @param firstPoint  the first reference point - and the reference point we chose as the main one of the plane.
     * @param secondPoint the second reference point.
     * @param thirdPoint  the third reference point.
     */
    public Plane(Point firstPoint, Point secondPoint, Point thirdPoint) {
        this.normal = null;
        this.p0 = firstPoint; // נקודות ייחוס למישור
    }

    /**
     * Constructs a new Plane object with a reference point and a normal vector.
     *
     * @param myPoint  the reference point of the plane.
     * @param myVector the normal vector of the plane.
     */
    public Plane(Point myPoint, Vector myVector) {
        this.normal = myVector.normalize();
        this.p0 = myPoint;
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
    public Vector getNormal(Point myPoint) {
        return normal;

    }
}
