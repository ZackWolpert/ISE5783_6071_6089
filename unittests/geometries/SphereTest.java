package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        Sphere mySphere = new Sphere(1,new Point(0,0,0));
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that getNormal function on Sphere is calculated correctly
        Point p1 = new Point(1, 0, 0);
        assertEquals(new Vector(1, 0, 0),mySphere.getNormal(p1),
                "Sphere getNormal function returns wrong normal");
    }
}