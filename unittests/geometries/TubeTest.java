package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Tube class
 * @author Zack and Nathan Wolpert
 *
 */
class TubeTest {

    /**
     * different geometries/primitives variables for the tests
     */
    Ray testRay = new Ray(new Point(0,0,0), new Vector(0, 1, 0));
    Tube testTube = new Tube(1, testRay);

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that getNormal function on Tube is calculated correctly - for points on side of Tube
        Point p1 = new Point(0, 3, 1);
        assertEquals(new Vector(0, 0, 1), testTube.getNormal(p1),
                "Tube getNormal function returns wrong normal");

        // =============== Boundary Values Tests ==================
        // T11: Test that getNormal function on Tube is calculated correctly  - for points on middle circle of Tube
        Point p2 = new Point(0, 0, 1);
        assertEquals(new Vector(0, 0, 1), testTube.getNormal(p2),
                "Tube getNormal function returns wrong normal");
    }

}