package renderer;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import geometries.Intersectable.GeoPoint;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static primitives.Util.alignZero;


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
    private Color calcColor(GeoPoint geoPoint,Ray ray){
        return scene.ambientLight.getIntensity()
               .add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }

    @Override
    public Color traceRay(Ray ray) {
        if(scene.geometries.findGeoIntersectionsHelper(ray) == null){return scene.background;}
        else {
            return calcColor(ray.findClosestGeoPoint(scene.geometries.findGeoIntersectionsHelper(ray)),ray);
        }
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir (); Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v)); if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;
        Double3 kd = intersection.geometry.getMaterial().kD, ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * alignZero(n.dotProduct(l))));
        double vr = v.scale(-1).dotProduct(r);
        if( alignZero(vr) <= 0 ){return Color.BLACK;} //
        return lightIntensity.scale(ks.scale(Math.pow(vr, nShininess)));
    }

    private Color calcDiffusive(Double3 kd, Vector l,Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd.scale(Math.abs(alignZero(n.dotProduct(l)))));
    }
}
