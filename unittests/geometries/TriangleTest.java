package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        Triangle myTriangle = new Triangle(p1, new Point(0, 1, 0),
                new Point(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that getNormal function on triangle is calculated correctly
        assertEquals(1,
                Math.abs(myTriangle.getNormal(p1).length()),
                0.00001, "Triangle getNormal function returns wrong normal");
    }
}