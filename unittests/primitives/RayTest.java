package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        // T01: The point in the middle of the list is closest to the beginning of the ray
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 1, 0));
        assertEquals(new Point(1, 1, 0),
                ray.findClosestPoint(List.of(
                        new Point(6, 5, 0),
                        new Point(1, 1, 0),
                        new Point(-5, 5, -5))),
                "Ray findClosestPoint function returns wrong value!");

        // =============== Boundary Values Tests ==================
        // T11: The point in the middle of the list is closest to the beginning of the ray
        assertNull(ray.findClosestPoint(null),
                "Ray findClosestPoint function returns wrong value!");

        // T12: The point that is the first element of the list is closest to the beginning of the ray
        assertEquals(new Point(1, 1, 0),
                ray.findClosestPoint(List.of(
                        new Point(1, 1, 0),
                        new Point(9, -2, 3),
                        new Point(-8, 5, -19))),
                "Ray findClosestPoint function returns wrong value!");

        // T13: The point that is the last element of the list is closest to the beginning of the ray
        assertEquals(new Point(1, 1, 0),
                ray.findClosestPoint(List.of(
                        new Point(16, -1, -3),
                        new Point(9, -2, 3),
                        new Point(1, 1, 0))),
                "Ray findClosestPoint function returns wrong value!");
    }
}