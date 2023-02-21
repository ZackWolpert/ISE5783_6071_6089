package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry{
    protected double radius;
    public RadialGeometry(Double radius)
    {
        this.radius = radius;
    }
    public Vector getNormal(Point myPoint) {
        return null;
    }
}
