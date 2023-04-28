package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Zack and Nathan Wolpert
 *
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // T01: check plane initialization
        try {
            new Plane(new Point(1, 0, 0),
                    new Point(0, 1, 0),
                    new Point(0, 0, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }

        // =============== Boundary Values Tests ==================
        // T10: The first point and second point converge on the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1),
                        new Point(0, 0, 1),
                        new Point(1, 0, 0)),
                "The first and second points are equal");

        // T11: All points are on same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(0, 0, 1),
                        new Point(1, 1, 1),
                        new Point(-1, -1, 1)),
                "All points are on the same line");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(1, 0, 0);
        Plane plane = new Plane(p1, new Point(0, 1, 0),
                new Point(0, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that getNormal function on Plane is calculated correctly
        assertEquals(new Vector(0,0,1),
             plane.getNormal(p1),
           "Plane getNormal function returns wrong normal ");
    }

    @Test
    void testFindIntersections() {

        Plane plane = new Plane(new Point(1, 0, 0), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============

        // T01: Ray intersects the plane
        Point p1 = new Point(1, 0, 0);
        List<Point> result1 = plane.findIntersections(new Ray(new Point(0, 0, 1),
                new Vector(1, 0, -1)));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(p1), result1, "Ray crosses plane");

        // T02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, -5), new Vector(0, 1, 0))),
                "Ray's line out of plane");
        // =============== Boundary Values Tests ==================

        // T11: Ray is parallel to the plane (not included)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, -5), new Vector(2, 1, 0))),
                "Ray's line out of plane");

        // T12:  Ray is parallel to the plane (included)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(2, 1, 0))),
                "Ray's line out of plane");

        // T13: Ray is orthogonal to the plane (before the plane)
        Point p2 = new Point(1, 1, 0);
        List<Point> result2 = plane.findIntersections(new Ray(new Point(1, 1, 1),
                new Vector(0, 0, -1)));
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(List.of(p2), result2, "Ray crosses plane");

        // T14: Ray is orthogonal to the plane (after the plane)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, -5), new Vector(0, 0, -1))),
                "Ray's line out of plane");

        // T15: Ray is orthogonal to the plane (in the plane)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0, -1))),
                "Ray's line out of plane");

        // T16: Ray is neither orthogonal nor parallel to and begins at the plane (𝑃0 is in the plane, but not the ray)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, -1))),
                "Ray's line out of plane");

        // T17: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, -1))),
                "Ray's line out of plane");
    }
}