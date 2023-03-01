package geometries;

import primitives.Ray;

/**
 * The Cylinder class represents a cylinder shape in three-dimensional space.
 * It is a subclass of the Tube class and adds a height property to represent the height of the cylinder.
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructs a Cylinder object with the specified radius, axis ray, and height.
     *
     * @param radius  the radius of the cylinder.
     * @param axisRay the axis ray of the cylinder.
     * @param height  the height of the cylinder.
     */
    public Cylinder(Double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * Returns the height of the cylinder.
     *
     * @return the height of the cylinder .
     */
    public double getHeight() {
        return height;
    }
}
