package primitives;

import static primitives.Util.isZero;

/**
 * A Vector class that represents a three-dimensional vector in space.
 * subclass of Point.
 */
public class Vector extends Point {
    /**
     * Constructs a new vector with the given three coordinates.
     *
     * @param x  the first coordinate of the vector
     * @param y the second coordinate of the vector
     * @param z  the third coordinate of the vector
     * @throws IllegalArgumentException if the vector is the ZERO vector
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(xyz.ZERO))
            throw new IllegalArgumentException("Vector cannot be ZERO vector");
    }

    /**
     * Constructs a new vector from the given Double3 object.
     *
     * @param double3 the Double3 object to construct the vector from
     * @throws IllegalArgumentException if the vector is the ZERO vector
     */
    Vector(Double3 double3) {
        super(double3);
        if (this.xyz.equals(xyz.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be ZERO vector");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vector other)
            return this.xyz.equals(other.xyz);
        return false;
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }

    /**
     * Returns a new vector that is the result of adding the given vector to this vector.
     *
     * @param v the vector to add to this vector
     * @return the new vector that is the result of adding the given vector to this vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Returns a new vector that is the result of scaling this vector by the given double.
     *
     * @param d the double to scale this vector by
     * @return the new vector that is the result of scaling this vector by the given double
     */
    public Vector scale(double d) {
        return new Vector(xyz.scale(d)); }

    /**
     * Returns the cross product of this vector and the given vector.
     *
     * @param other the vector to compute the cross product with
     * @return the cross product of this vector and the given vector
     */
    public Vector crossProduct(Vector other) {
        return new Vector(xyz.d2 * other.xyz.d3 - xyz.d3 * other.xyz.d2,
                xyz.d3 * other.xyz.d1 - xyz.d1 * other.xyz.d3,
                xyz.d1 * other.xyz.d2 - xyz.d2 * other.xyz.d1);
    }

    /**
     * Returns the squared length of this vector.
     *
     * @return the squared length of this vector
     */

    public double lengthSquared() {
        return (xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3);
    }

    /**
     * Returns the length of the vector.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Returns a normalized vector.
     *
     * @return the normalized vector using reduce method form Vector property xyz .
     */
    public Vector normalize() {
        return new Vector((xyz.reduce(length())));
    }

    /**
     * Returns the dot product of this vector and the specified vector.
     *
     * @param v the vector to calculate the dot product with
     * @return the dot product of this vector and the specified vector
     */
    public double dotProduct(Vector v) {
        return (xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3);
    }
}
