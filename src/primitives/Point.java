package primitives;

/**
 * The Point class represents a point in three-dimensional space.
 * It encapsulates a Double3 object to store its x, y, and z coordinates.
 */
public class Point {
    /**
     * The Double3 object storing the x, y, and z coordinates of the point.
     */
    final Double3 xyz;

    /**
     * Constructs a Point object with the specified x, y, and z coordinates.
     *
     * @param x  the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @param z  the z coordinate of the point.
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a Point object from a given Double3 object.
     *
     * @param double3 the Double3 object containing the coordinates of the point.
     */
    Point(Double3 double3) {
        this.xyz = double3;
    }

    /**
     * Gets the x coordinate of the point
     *
     * @return the coordinate
     */
    public double getX() {
        return xyz.d1;
    }
    public double getY() {
        return xyz.d2;
    }
    public double getZ() {
        return xyz.d3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point other)
            return this.xyz.equals(other.xyz);
        return false;

    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return " " + xyz;
    }

    /**
     * Aligns the number to zero if it is almost zero
     *
     * @param p the Point to subtract from this Point to mine to get return Vector .
     * @return new Vector made from subtract of the two points .
     */
    public Vector subtract(Point p) {
        return new Vector(Util.alignZero((this.xyz.subtract(p.xyz)).d1),
                Util.alignZero((this.xyz.subtract(p.xyz)).d2),
                Util.alignZero((this.xyz.subtract(p.xyz)).d3));
    }

    /**
     * Adds a vector to this point and returns the resulting point.
     *
     * @param v the vector to add to this point.
     * @return the point obtained by adding myVector to this point.
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * Returns the squared distance between this point and another point.
     *
     * @param p the other point.
     * @return the squared distance between this point and myPoint.
     */
    public double distanceSquared(Point p) {
        return (this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1) +
                (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2) +
                (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3);
    }

    /**
     * Returns the distance between this point and another point.
     *
     * @param p the other point.
     * @return the distance between this point and myPoint .
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }
}
