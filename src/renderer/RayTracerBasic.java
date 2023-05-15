package renderer;

import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import geometries.Intersectable.GeoPoint;

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
     * @param geoPoint The point given .
     */
    private Color calcColor(GeoPoint geoPoint){
        return scene.ambientLight.getIntensity()
               .add(geoPoint.geometry.getEmission());
    }

    @Override
    public Color traceRay(Ray ray) {
        if(scene.geometries.findGeoIntersectionsHelper(ray) == null){return scene.background;}
        else {
            return calcColor(ray.findClosestGeoPoint(scene.geometries.findGeoIntersectionsHelper(ray)));
        }
    }
}
