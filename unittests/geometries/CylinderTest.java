package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author Zack and Nathan Wolpert
 *
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder myCylinder = new Cylinder(4d,new Ray(
                new Point(0, 0, 0),
                new Vector(0, 1, 0)), 4);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal to cylinder at side - normal length being equal to 1)
        var myNormal = myCylinder.getNormal(new Point(4, 1, 0));
        assertEquals(new Vector(1, 0, 0), myNormal,
                " getNormal function of cylinder doesn't work correctly");

        // TC02: Test normal at top base of cylinder
        myNormal = myCylinder.getNormal(new Point(0, 4, -2));
        assertEquals(new Vector(0, 1, 0), myNormal,
                " getNormal function of cylinder doesn't work correctly");

        // TC03: Test normal at bottom base of cylinder
        myNormal = myCylinder.getNormal(new Point(0, 0, 2));
        assertEquals(new Vector(0, -1, 0), myNormal,
                " getNormal function of cylinder doesn't work correctly");

        // =============== Boundary Values Tests ==================
        // TC11: Test normal at the center of top base of the cylinder
        myNormal = myCylinder.getNormal(new Point(0, 4, 0));
        assertEquals(new Vector(0, 1, 0), myNormal,
                " getNormal function of cylinder doesn't work correctly");

        // TC12: Test normal at the center of bottom base of the cylinder
        myNormal = myCylinder.getNormal(new Point(0, 0, 0));
        assertEquals(new Vector(0, -1, 0), myNormal,
                " getNormal function of cylinder doesn't work correctly");

    }

}