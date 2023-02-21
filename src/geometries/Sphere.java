package geometries;

import primitives.Point;

public class Sphere extends RadialGeometry{
    private Point center;
    private double radius;

    public Sphere(Double radius) {
        super(radius);
    }
}
