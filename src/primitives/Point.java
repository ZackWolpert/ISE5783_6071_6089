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
     * @param firstCoordinate  the x coordinate of the point.
     * @param secondCoordinate the y coordinate of the point.
     * @param thirdCoordinate  the z coordinate of the point.
     */
    public Point(double firstCoordinate, double secondCoordinate, double thirdCoordinate) {
        this.xyz = new Double3(firstCoordinate, secondCoordinate, thirdCoordinate);
    }

    /**
     * Constructs a Point object from a given Double3 object.
     *
     * @param myDouble3 the Double3 object containing the coordinates of the point.
     */
    Point(Double3 myDouble3) {
        this.xyz = myDouble3;
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
     * @param myPoint the Point to subtract from this Point to mine to get return Vector .
     * @return new Vector made from subtract of the two points .
     */
    public Vector subtract(Point myPoint) {
        return new Vector(Util.alignZero((this.xyz.subtract(myPoint.xyz)).d1), Util.alignZero((this.xyz.subtract(myPoint.xyz)).d2), Util.alignZero((this.xyz.subtract(myPoint.xyz)).d3));
    }

    /**
     * Adds a vector to this point and returns the resulting point.
     *
     * @param myVector the vector to add to this point.
     * @return the point obtained by adding myVector to this point.
     */
    public Point add(Vector myVector) {
        return new Point(xyz.add(myVector.xyz));
    }

    /**
     * Returns the squared distance between this point and another point.
     *
     * @param myPoint the other point.
     * @return the squared distance between this point and myPoint.
     */
    public double distanceSquared(Point myPoint) {
        return (this.xyz.d1 - myPoint.xyz.d1) * (this.xyz.d1 - myPoint.xyz.d1) + (this.xyz.d2 - myPoint.xyz.d2) * (this.xyz.d2 - myPoint.xyz.d2) + (this.xyz.d3 - myPoint.xyz.d3) * (this.xyz.d3 - myPoint.xyz.d3);
    }

    /**
     * Returns the distance between this point and another point.
     *
     * @param myPoint the other point.
     * @return the distance between this point and myPoint .
     */
    public double distance(Point myPoint) {
        return Math.sqrt(distanceSquared(myPoint));
    }
}
