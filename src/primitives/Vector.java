package primitives;

/**
 * A Vector class that represents a three-dimensional vector in space.
 * subclass of Point .
 */
public class Vector extends Point {
    /**
     * Constructs a new vector with the given three coordinates.
     *
     * @param firstCoordinate  the first coordinate of the vector
     * @param secondCoordinate the second coordinate of the vector
     * @param thirdCoordinate  the third coordinate of the vector
     * @throws IllegalArgumentException if the vector is the ZERO vector
     */
    public Vector(double firstCoordinate, double secondCoordinate, double thirdCoordinate) {
        super(firstCoordinate, secondCoordinate, thirdCoordinate);
        if (this.xyz.equals(xyz.ZERO))
            throw new IllegalArgumentException("Vector cannot be ZERO vector");
    }

    /**
     * Constructs a new vector from the given Double3 object.
     *
     * @param myDouble3 the Double3 object to construct the vector from
     * @throws IllegalArgumentException if the vector is the ZERO vector
     */
    Vector(Double3 myDouble3) {
        super(myDouble3);
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
     * @param myVector the vector to add to this vector
     * @return the new vector that is the result of adding the given vector to this vector
     */
    public Vector add(Vector myVector) {
        return new Vector(xyz.add(myVector.xyz));
    }

    /**
     * Returns a new vector that is the result of scaling this vector by the given double.
     *
     * @param myDouble the double to scale this vector by
     * @return the new vector that is the result of scaling this vector by the given double
     */
    public Vector scale(double myDouble) {
        return new Vector(xyz.scale(myDouble));
    }

    /**
     * Returns the cross product of this vector and the given vector.
     *
     * @param myVector the vector to compute the cross product with
     * @return the cross product of this vector and the given vector
     */
    public Vector crossProduct(Vector myVector) {
        return new Vector(xyz.d2 * myVector.xyz.d3 - xyz.d3 * myVector.xyz.d2, xyz.d3 * myVector.xyz.d1 - xyz.d1 * myVector.xyz.d3, xyz.d1 * myVector.xyz.d2 - xyz.d2 * myVector.xyz.d1);
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
     * @param myVector the vector to calculate the dot product with
     * @return the dot product of this vector and the specified vector
     */
    public double dotProduct(Vector myVector) {
        return (xyz.d1 * myVector.xyz.d1 + xyz.d2 * myVector.xyz.d2 + xyz.d3 * myVector.xyz.d3);
    }
}
