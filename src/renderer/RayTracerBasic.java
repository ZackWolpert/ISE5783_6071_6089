package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;


/**
 * The RayTracerBasic class is a basic implementation of a ray tracer that extends the RayTracerBase class.
 * It provides a simple implementation of the traceRay() method.
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     * Constructs a RayTracerBase object with the specified scene.
     * @param scene The scene to be rendered.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Returns Color for given Point
     * @param point The point given .
     */
    private Color calcColor(Point point){return scene.ambientLight.getIntensity();}

    @Override
    public Color traceRay(Ray ray) {
        if(scene.geometries.findIntersections(ray) == null){return scene.background;}
        else {
            return calcColor(ray.findClosestPoint(scene.geometries.findIntersections(ray)));
        }
    }
}
