package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
/**
 * Unit tests for primitives.Vector class
 * @author Zack and Nathan Wolpert
 *
 */
class VectorTest {

    /**
     * Vectors for the tests
     */
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(0, 3, -2);
    Vector v3 = new Vector(0, 3, 4);


    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //T01: Test that the add function is calculated correctly
        assertEquals(
                new Vector(1, 5, 1),
                v1.add(v2),
                "Vector add function does not work properly"
        );

        // =============== Boundary Values Tests ==================
        // T11: Test that the add function off opposites vectors throws exception
        assertThrows(
                IllegalArgumentException.class, () -> v1.add(v1.scale(-1)),
                "subtract for point on same point does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        //T11: Test that the new scaled vector from scale function has a scaled length
        assertEquals(
                (v1.add(v2)), new Vector(1, 5, 1),
                "Vector scale function does not work properly");

        // =============== Boundary Values Tests ==================
        //T12: Test that the scale function with zero returns Zero Vector Exception
        assertThrows(
                IllegalArgumentException.class, () -> v1.scale(0),
                "The scale function with parameter zero does not throw an exception ");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v2);

        // T01: Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,
                "crossProduct wrong result length");

        // T02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),
                "crossProduct result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),
                "crossProduct result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // T11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(
                IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that lengthSquared function is calculated correctly
        assertEquals(14, v1.lengthSquared(), 0.00001,
                "lengthSquared function returns wrong value");

    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that length function value is calculated correctly
        assertEquals(5, v3.length(), 0.00001,
                "length function gave wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v4 = new Vector(0, 0, 4);
        Vector myNormal = v4.normalize();
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that normalize function returns vector length 1
        assertEquals(1d, myNormal.lengthSquared(), 0.0001,
                "wrong normalized vector length");

        // T02: Test that normalize vector and the original vector have the same direction
        assertThrows(IllegalArgumentException.class, () -> v4.crossProduct(myNormal),
                "normalized vector is not in the same direction ");
        // T03: Test that normalize vector function returns correct Vector
        assertEquals(new Vector(0,0,1), myNormal,
                "wrong normalized vector");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v5 = new Vector(2, 4, 6);
        // T01: Test that dot product function return value is calculated correctly
        assertEquals(28, v1.dotProduct(v5), 0.00001,
                "The dot product does not work correctly");

        // T02: test that orthogonal vectors return 0 on dot product
        assertTrue(
                isZero(v1.dotProduct(v2)),
                "The dot product of orthogonal vectors does not work correctly");

        // T03: test that dot product returns the negative value of the regular dot product when working in different order
        assertEquals(-1 * v2.dotProduct(v5), v5.dotProduct(v2), 0.00001,
                "The dot product does not work correctly");
    }
}