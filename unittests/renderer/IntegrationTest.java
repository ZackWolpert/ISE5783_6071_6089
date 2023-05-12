package renderer;

import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Vector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Geometries Integration Tests
 */
public class IntegrationTest {

    /**
     * Camera for these Tests .
     */
    final Camera camera = new Camera(
            new Point(0,0,0),
            new Vector(0, 0, 1),
            new Vector(0, -1, 0))
            .setVPDistance(1)
            .setVPSize(3, 3);
    int numOfIntersectionPoints;

    /**
     * Camera and Sphere intersections Test .
     */
    @Test
    void CameraSphereIntersections() {
        // T01: sphere radius is 1, two intersection points
        Sphere sphere1 = new Sphere(1,new Point(0, 0, 3));
        numOfIntersectionPoints = countIntersections(sphere1, 3, 3);
        assertEquals(2, numOfIntersectionPoints,
                "wrong intersection points number!");

        // T02: sphere radius is 2.5, 18 intersection points
        Sphere sphere2 = new Sphere(2.5,new Point(0, 0, 3));
        numOfIntersectionPoints = countIntersections(sphere2, 3, 3);
        assertEquals(18, numOfIntersectionPoints,
                "wrong intersection points number!");

        // T03: sphere radius is 2, 10 intersection points
        Sphere sphere3 = new Sphere(2,new Point(0, 0, 2.5));
        numOfIntersectionPoints = countIntersections(sphere3, 3, 3);

        assertEquals(10, numOfIntersectionPoints,
                "wrong intersection points number!");

        // T04: sphere radius is 4, 9 intersection points -  camera is inside the sphere
        Sphere sphere4 = new Sphere(4,new Point(0, 0, 1));
        numOfIntersectionPoints = countIntersections(sphere4, 3, 3);
        assertEquals(9, numOfIntersectionPoints,
                "wrong intersection points number!");

        // T05: sphere radius is 0.5, 0 intersection points - sphere is behind the camera
        Sphere sphere5 = new Sphere(0.5,new Point(0, 0, -1));
        numOfIntersectionPoints = countIntersections(sphere5, 3, 3);
        assertEquals(0, numOfIntersectionPoints,
                "wrong intersection points number!");
    }

    /**
     * Camera and Plane intersections Test .
     */
    @Test
    void CameraPlaneIntersections() {
        // T01: the plane and the view plane are parallel, 9 intersection points
        Plane plane1 = new Plane(new Point(0, 0, 5), new Vector(0, 0, 1));
        numOfIntersectionPoints = countIntersections(plane1, 3, 3);
        assertEquals(9, numOfIntersectionPoints,
                "wrong intersection points number!");

        // T02: the plane and the view plane are not parallel, 9 intersection points
        Plane plane2 = new Plane(new Point(0, -3, 1), new Vector(0, -0.5, 1));
        numOfIntersectionPoints = countIntersections(plane2, 3, 3);
        assertEquals(9, numOfIntersectionPoints,
                "wrong intersection points number!");

        // T03: no intersection with the rays from the bottom row of the view plane, 6 intersection points
        Plane plane3 = new Plane(new Point(0, -3, 1), new Vector(0, -2, 1));
        numOfIntersectionPoints = countIntersections(plane3, 3, 3);
        assertEquals(6, numOfIntersectionPoints,
                "wrong intersection points number!");
    }

    /**
     * Camera and Triangle intersections Test .
     */
    @Test
    void CameraTriangleIntersections() {
        // T01: intersect only with the canter ray, one intersection points
        Triangle triangle1 = new Triangle(new Point(0, -1, 2),
                new Point(1, 1, 2), new Point(-1, 1, 2));
        numOfIntersectionPoints = countIntersections(triangle1, 3, 3);
        assertEquals(1, numOfIntersectionPoints,
                "wrong intersection points number!");

        // T02: intersect only with the canter ray and the above one, two intersection points
        Triangle triangle2 = new Triangle(new Point(0, -20, 2),
                new Point(1, 1, 2), new Point(-1, 1, 2));
        numOfIntersectionPoints = countIntersections(triangle2, 3, 3);
        assertEquals(2, numOfIntersectionPoints,
                "wrong intersection points number!");
    }

    /**
     * Auxiliary function - Checks the amount of intersection points in a geometry from a camera.
     *
     * @param intersectable The geometry to check intersections with.
     * @param nX
     * @param nY
     * @return The amount of intersection points from the given camera.
     */
    int countIntersections(Intersectable intersectable, int nX, int nY) {
        int result = 0;
        for (int i = 0; i < nX; ++i) {
            for (int j = 0; j < nY; ++j) {
                List<Point> points =
                        intersectable.findIntersections(camera.constructRay(nX, nY, j, i));
                if (points != null)
                    result += points.size();
            }
        }
        return result;
    }
}
