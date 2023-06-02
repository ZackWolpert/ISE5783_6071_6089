package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;


import static java.lang.System.out;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Sphere class represents a sphere shape in three-dimensional space.
 * It is a subclass of the RadialGeometry class and adds a center property to represent the center of the sphere.
 * important L sphere equation : (x-firstCoordinate)^2 + (y-secondCoordinate)^2 + (z-thirdCoordinate)^2 = radius, while (firstCoordinate,secondCoordinate,thirdCoordinate) represents the center of the sphere.
 * */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a Sphere object with the specified radius and center.
     *
     * @param radius the radius of the sphere.
     * @param center the center of the sphere .
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * returns the center of the Sphere
     *
     * @return the center of the Sphere
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point other) {
        // assuming the point is on the surface od the sphere
        return other.subtract(center).normalize();

    }

    /**
     * Find the intersections between a sphere and a ray.
     *
     * @param ray the ray to intersect with the sphere.
     * @return a List of GeoPoint objects representing the intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        // check if point is inside sphere
        try { // if P0 point of ray is same as center - this would throw ZERO Vector Exeption .
            Vector w = ray.getP0().subtract(center);
            double a = ray.getDir().lengthSquared();
            double b = w.scale(2).dotProduct(ray.getDir());
            double c = w.lengthSquared() - radius * radius;
            // now we use the quadratic formula - to get points
            if(b*b-4*a*c > 0 ){ // check if this is > 0 for the sqrt .
                double t1 = alignZero((-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a);
                double t2 = alignZero((-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a);
                if(t1>0 && alignZero(t1-maxDistance) <= 0){ // return only point of ray intersection ( not of opposite direction )
                    if(t2>0 && alignZero(t2-maxDistance) <= 0){
                        return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
                    }
                    else{
                        return List.of(new GeoPoint(this,ray.getPoint(t1)));
                    }
                } else{
                    if(t2>0 && alignZero(t2-maxDistance) <= 0){
                        return List.of(new GeoPoint(this,ray.getPoint(t2)));
                    }
                    else{
                        return null;
                    }
                }
            } else
                return null;
        }catch (IllegalArgumentException zeroVector){ // if P0 is same point as center - we calculate accordingly
            double a = ray.getDir().lengthSquared();
            double b = 0;
            double c =  - radius * radius;
            double t1 = alignZero((-b + Math.sqrt(b * b - 4 * a * c)) / 2 * a);
            double t2 = alignZero((-b - Math.sqrt(b * b - 4 * a * c)) / 2 * a);
            if(t1>0 && alignZero(t1-maxDistance) <= 0){ // we take the point where t > 0 - which is the intersection with real direction of ray .
                return List.of(new GeoPoint(this,ray.getPoint(t1)));
            } else{
                if(alignZero(t2-maxDistance) <= 0)
                {
                    return List.of(new GeoPoint(this,ray.getPoint(t2)));
                }
                else{
                    return null;
                }
            }
        }
    }
}
