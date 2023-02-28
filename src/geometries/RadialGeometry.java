package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The RadialGeometry abstract class represents a geometry with a radial component, such as a sphere or a cylinder.
 * It implements the Geometry interface and provides a radius property.
 */
public abstract class RadialGeometry implements Geometry {
    /**
     * The radius of the radial geometry.
     */
    protected final double radius;

    /**
     * Constructs a RadialGeometry object with the specified radius.
     *
     * @param radius the radius of the radial geometry.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * Returns the radius of the radial geometry.
     *
     * @return the radius of the radial geometry.
     */
    public double getRadius() {
        return radius;
    }
}
