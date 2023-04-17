package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
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
        Plane myPlane = new Plane(p1, new Point(0, 1, 0),
                new Point(0, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that getNormal function on Plane is calculated correctly
        assertEquals(new Vector(0,0,1),
             myPlane.getNormal(p1),
           "Plane getNormal function returns wrong normal ");
    }

}