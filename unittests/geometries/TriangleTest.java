package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Triangle class
 * @author Zack and Nathan Wolpert
 *
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(1, 0, 0);
        Triangle triangle = new Triangle(p1, new Point(0, 1, 0),
                new Point(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that getNormal function on triangle is calculated correctly
        assertEquals(1,
                Math.abs(triangle.getNormal(p1).length()),
                0.00001, "Triangle getNormal function returns wrong normal");
    }

    @Test
    void testFindIntersections() {
        Point x = new Point(4, 0, 4);
        Point y = new Point(0, 4, 4);
        Point z = new Point(-4, 0, 4);
        Triangle triangle = new Triangle(x, y, z);

        // ============ Equivalence Partitions Tests ==============

        // T01: the point is inside the triangle
        Point p1 = new Point(0, 2, 4);
        List<Point> result1 = triangle.findIntersections(new Ray(new Point(0, 2, 0),
                new Vector(0, 0, 1)));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(p1), result1, "Ray crosses triangle");

        // T02: the point is outside against edge
        assertNull(triangle.findIntersections(new Ray(new Point(2, 3, 2), new Vector(0, 0, 1))),
                "Ray's line out of plane");

        // T03: the point is outside against vertex
        assertNull(triangle.findIntersections(new Ray(new Point(-5, -2, 6), new Vector(0, 0, -1))),
                "Ray's line out of plane");

        // =============== Boundary Values Tests ==================
        // T11: the point is on edge
        assertNull(triangle.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, 1))),
                "Ray's line out of plane");

        // T12: the point is in vertex
        assertNull(triangle.findIntersections(new Ray(new Point(0, 4, 6), new Vector(0, 0, -1))),
                "Ray's line out of plane");

        // T13: the point is on edge's continuation
        assertNull(triangle.findIntersections(new Ray(new Point(6, 0, 4), new Vector(0, 0, -1))),
                "Ray's line out of plane");

    }
}