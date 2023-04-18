package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

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

    @Override
    public Vector getNormal(Point other) {
        // we assume point is on the cylinder, so we check if it is on top, bottom or side.
        Point myCenterBottomPoint = axisRay.getP0();
        Point myCenterTopPoint = axisRay.getP0().add(axisRay.getDir().scale(height));
        Vector myRayDir = axisRay.getDir();
        if (other.equals(myCenterTopPoint) || isZero(other.subtract(myCenterTopPoint).dotProduct(myRayDir)))  // on the top
            return myRayDir;
        else if (other.equals(myCenterBottomPoint) || isZero(other.subtract(myCenterBottomPoint).dotProduct(myRayDir)))  //on the bottom
            return myRayDir.scale(-1); // same vector but opposite direction
        else  // on the side of cylinder - just like Tube .
            return super.getNormal(other);
    }
}
