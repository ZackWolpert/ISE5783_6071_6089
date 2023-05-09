package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Zack and Nathan Wolpert
 *
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(1,new Point(0,0,0));
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that getNormal function on Sphere is calculated correctly
        Point p1 = new Point(1, 0, 0);
        assertEquals(new Vector(1, 0, 0),sphere.getNormal(p1),
                "Sphere getNormal function returns wrong normal");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntsersections() {
        Sphere sphere = new Sphere(1d, new Point (1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Point p3 = new Point(1, 1, 0);
        List<Point> result3 = sphere.findIntersections(new Ray(new Point(1, 0.5, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(List.of(p3), result3, "Ray crosses sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(0, 2, 0), new Vector(0, 1, 0))),
               "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Point p4 = new Point(2, 0, 0);
        List<Point> result4 = sphere.findIntersections(new Ray(new Point(1, 1, 0),
                new Vector(1, -1, 0)));
        assertEquals(1, result4.size(), "Wrong number of points");
        assertEquals(List.of(p4), result4, "Ray crosses sphere");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(-1, 1, 0))),
                "Ray's line out of sphere");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        Point p5 = new Point(0, 0, 0);
        Point p6 = new Point(2, 0, 0);
        List<Point> result5 = sphere.findIntersections(new Ray(new Point(2.5, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals(2, result5.size(), "Wrong number of points");
        if (result5.get(0).getX() > result5.get(1).getX())
            result5 = List.of(result5.get(1), result5.get(0));
        assertEquals(List.of(p5, p6), result5, "Ray crosses sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        Point p7 = new Point(2, 0, 0);
        List<Point> result6 = sphere.findIntersections(new Ray(new Point(0, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals(1, result6.size(), "Wrong number of points");
        assertEquals(List.of(p7), result6, "Ray crosses sphere");

        // TC15: Ray starts inside (1 points)
        List<Point> result7 = sphere.findIntersections(new Ray(new Point(1.5, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals(1, result7.size(), "Wrong number of points");
        assertEquals(List.of(p7), result7, "Ray crosses sphere");

        // TC16: Ray starts at the center (1 points)
        List<Point> result8 = sphere.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals(1, result8.size(), "Wrong number of points");
        assertEquals(List.of(p7), result8, "Ray crosses sphere");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, 0, 0))),
                "Ray's line out of sphere");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(-1, 0, 0))),
                "Ray's line out of sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, -1, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, -1, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 1, 0))),
                "Ray's line out of sphere");
    }
}