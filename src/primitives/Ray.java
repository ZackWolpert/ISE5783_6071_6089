package primitives;

import java.util.List;

import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;

/**
 * A ray class represents a line in 3D space defined by an origin point and a direction vector.
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * head of ray offset size for shading rays
     */
    private static final double DELTA = 0.1;

    /**
     * Constructs a new ray with the given origin point and direction vector.
     *
     * @param p0  the origin point of the ray
     * @param dir the direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * constructor for construction a ray with moved head in delta direction
     * @param p0 initial head of ray
     * @param direction direction of ray
     * @param normal normal to geometry
     */
    public Ray(Point p0, Vector direction, Vector normal){
        double ln = direction.dotProduct(normal);
        if (ln < 0) {
            normal = normal.scale(-1);
        }
        this.p0 = p0.add(normal.scale(DELTA));
        this.dir = direction;
    }
    /**
     * Finds the closest point in a list of points to a given reference point.
     * @param points The list of points to search for the closest point.
     * @return The closest point to the reference point.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint in a list of GeoPoints to a given reference point.
     * @param geoPoints The list of GeoPoints to search for the closest point.
     * @return The closest GeoPoint to the reference point.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints){
        if (geoPoints == null){
            return null;
        }
        double distance = p0.distance(geoPoints.get(0).point);
        GeoPoint geoPointRet = geoPoints.get(0);
        for(int i = 0;i<geoPoints.size();i++){
            if(p0.distance(geoPoints.get(i).point) < distance){
                distance = p0.distance(geoPoints.get(i).point);
                geoPointRet = geoPoints.get(i);
            }
        }
        return geoPointRet;
    }

    /**
     * Returns the origin point of the ray.
     *
     * @return the origin point of the ray
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return the direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) {
        if (isZero(t)) {
            return this.p0;
        }
        return this.p0.add(this.dir.scale(t));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return p0.equals(other.p0) && dir.equals(other.dir);
        return false;
    }

    @Override
    public String toString() {
        return p0.toString() + "" + dir.toString();
    }

}
