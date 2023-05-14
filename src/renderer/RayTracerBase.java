package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * The RayTracerBase class is an abstract base class for ray tracing algorithms.
 * It provides a common structure and interface for implementing ray tracing functionality.
 */
public abstract class RayTracerBase {

    /**
     * The protected scene to be rendered by the ray tracer.
     */
    protected Scene scene;

    /**
     * Constructs a RayTracerBase object with the specified scene.
     * @param scene The scene to be rendered.
     */
    public RayTracerBase(Scene scene){
        this.scene = scene;
    }

    /**
     * Traces a ray and calculates the color at the intersection point.
     * abstract method - must be implemented by subclasses.
     * @param ray The ray to trace.
     * @return The color at the intersection point.
     */
    public abstract Color traceRay(Ray ray);

}
