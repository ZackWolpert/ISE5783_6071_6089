package geometries;

import primitives.Point;
import primitives.Vector;

import primitives.Color;

/**
 * The Geometry interface represents a geometric object in a three-dimensional space.
 * It provides methods for getting the normal vector of the object.
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;

    /**
     * Get emission Color of Geometry
     *
     * @return the emission Color
     */
    public Color getEmission() {return emission;}

    /**
     * Setter of the emission color
     * @param emission the emission color of the Geometry
     * @return the updated Geometry
     */
    public Geometry setEmission(Color emission) {this.emission = emission;return this;}

    /**
     * Returns the normal vector of the geometry object at the specified point.
     *
     * @param other the point on the geometry object.
     * @return the normal vector of the geometry object at the specified point.
     */
    public abstract Vector getNormal(Point other);
}
