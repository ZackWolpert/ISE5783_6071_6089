package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Zack and Nathan Wolpert
 *
 */
class PointTest {

    /**
     * points for the tests
     */
    Point p1 = new Point(1, 1, 1);
    Point p2 = new Point(-1, -1, -1);

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that point subtract another point function is calculated correctly
        assertEquals((p1.subtract(p2)), new Vector(2, 2, 2),
                "Point subtract function returns wrong answer ");

        // =============== Boundary Values Tests ==================
        // T11: Test that point subtract the same point throws exception ( zero Vector )
        assertThrows(
                IllegalArgumentException.class, () -> p1.subtract(p1),
                "subtract point with the same point does not throw an exception as needed");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that point plus vector function is calculated correctly
        assertEquals(
                new Point(0,0,0),
                p1.add(new Vector(-1, -1, -1)),
                "Point add function returns wrong answer");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the squared distance between two points function is calculated correctly
        assertEquals(12, p1.distanceSquared(p2), 0.00001,
                "Point distanceSquared function returns wrong distance");

        // =============== Boundary Values Tests ==================
        // T11: Test that distanceSquared function on the same point returns 0 as needed
        assertEquals(0, p1.distanceSquared(p1), 0.00001,
                "Point distanceSquared function on the same point does not return zero");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the distance function between two points is calculated correctly
        assertEquals(Math.sqrt(12), p1.distance(p2), 0.00001,
                "Point distance function returns wrong distance ");

        // =============== Boundary Values Tests ==================
        // T11: Test that the distance function between the same point returns 0 as needed
        assertEquals(0, p1.distance(p1), 0.00001,
                "Point distance function on the same point does not return zero");
    }

}