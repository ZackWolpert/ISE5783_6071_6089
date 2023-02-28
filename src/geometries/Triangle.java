package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Triangle class represents a triangle in three-dimensional space.
 * It is a subclass of the Polygon class and is defined by three points.
 */

public class Triangle extends Polygon {
    /**
     * Constructs a Triangle object with the specified points.
     *
     * @param firstPoint  the first point of the triangle.
     * @param secondPoint the second point of the triangle.
     * @param thirdPoint  the third point of the triangle.
     */
    public Triangle(Point firstPoint, Point secondPoint, Point thirdPoint) {
        super(firstPoint, secondPoint, thirdPoint);
    }
}
