package renderer;

import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import geometries.Triangle;
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

    private static final double EPS = 0.1;

    /**
     * Stopping conditions in the recursion of transparencies/reflections
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;



    /**
     * Constructs a RayTracerBase object with the specified scene.
     * @param scene The scene to be rendered.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * construct the refracted ray from geometry
     *
     * @param point geometry's point
     * @param v     initial intersection ray direction
     * @param n     normal to geometry at point
     * @return refracted ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * construct the reflected ray from geometry
     *
     * @param point geometry's point
     * @param v     initial intersection ray direction
     * @param n     normal to geometry at point
     * @return reflected ray
     */

    private Ray constructReflectedRay(Point point,Vector v,Vector n){
        Vector r = v.subtract(n.scale(2*n.dotProduct(v)));
        return new Ray(point,r,n);

    }

    /**
     * Returns Color for given Point
     * @param intersection The point given .
     */
    private Color calcColor(GeoPoint intersection,Ray ray,int level, Double3 k){
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }
    /**
     * Returns Color for given Point
     * @param intersection The point given .
     */
    private Color calcColor(GeoPoint intersection, Ray ray){
        return calcColor(intersection, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint intersection = findClosestIntersection(ray);
        if(intersection == null){
            return scene.background;
        }
        else {
            return calcColor(intersection,ray);
        }
    }

    /**
     * calculate local effects of lights in scene - diffusive and specular
     * @param intersection point of intersection
     * @param ray      initial intersecting ray
     * @return local effects on point in scene
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0){
            return Color.BLACK;
        }
        int nShininess = intersection.geometry.getMaterial().nShininess;
        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(intersection, lightSource, l, n);
                if (ktr.product(k).lowerThan(MIN_CALC_COLOR_K) == false) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd,l,n,lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * calculate global effects on point (refraction and reflection)
     *
     * @param gp    point of intersection
     * @param v     initial intersecting ray
     * @param level level of recursion
     * @param k     attenuation factor
     * @return global effects additive
     */

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.kR.product(k);
        if (kkr.lowerThan(MIN_CALC_COLOR_K) == false)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        Double3 kkt = material.kT.product(k);
        if (kkt.lowerThan(MIN_CALC_COLOR_K) == false)
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * calculate specific affect each time - refraction or reflection.
     *
     * @param ray   constructed reflected/refracted ray
     * @param level level of recursion
     * @param kx    kR / kT
     * @param kkx   kkR/ kkT
     * @return specific global effect
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
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

    /**
     * check if a point in scene is not shaded from a specific light source
     * @param l vector from light source to point
     * @param light the LightSource
     * @param n normal to geometry at point
     * @param gp geometry and point
     * @return is not shaded
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n,LightSource light){
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? EPS : -EPS);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,light.getDistance(gp.point));
        if(intersections == null){return true;}
        for (GeoPoint geoPoint: intersections){
            if(geoPoint.geometry.getMaterial().kT == Double3.ZERO){
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    /**
     * checks the transparency of a geopoint
     * @param geoPoint geometry and point
     * @param l vector from light source to point
     * @param n normal to geometry at point
     * @param ls the LightSource
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n){
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
                ls.getDistance(geoPoint.point));
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint intersection : intersections) {
            ktr = intersection.geometry.getMaterial().kT.product(ktr);
            if (ktr.equals(Double3.ZERO)){
                return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * @param ray ray to intersect scene
     * @return closest intersection on ray to ray head
     */
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

}
