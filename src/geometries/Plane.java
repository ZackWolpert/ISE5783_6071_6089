package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    private Point p0;
    private Vector normal;
    public Point getP0(){
        return  p0;
    }
    public Vector getNormal(){
        return normal;
    }
    public Plane(Point firstPoint, Point secondPoint, Point thirdPoint){
        Vector v1 = secondPoint.subtract(firstPoint);
        Vector v2 = thirdPoint.subtract(firstPoint);
        this.normal = v1.crossProduct(v2).normalize();
        this.p0 = firstPoint; // נקודות ייחוס למישור
    }
    public Plane(Point myPoint, Vector myVector){
        this.normal = myVector.normalize();
        this.p0 = myPoint;
    }

    public Vector getNormal(Point myPoint) {
        return normal;

    }
}
