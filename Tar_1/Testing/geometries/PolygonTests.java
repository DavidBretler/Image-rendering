/**
 *
 */
package geometries;


import primitives.Point3D;
import primitives.Vector;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * { @link geometries.PolygonPolygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */

    // ============ Equivalence Partitions Tests ==============
    @Test
    public void testConstructor1() {
        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon
                    (
                            new Point3D(0, 0, 1),
                            new Point3D(1, 0, 0),
                            new Point3D(0, 1, 0),
                            new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }
    }


    @Test
    public void testConstructor2() {
        // TC02: Wrong vertices order
        try {
            new Polygon
                    (
                            new Point3D(0, 0, 1),
                            new Point3D(0, 1, 0),
                            new Point3D(1, 0, 0),
                            new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {
        }
    }


    @Test
    public void testConstructor3() {
        // TC03: Not in the same plane
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {
        }
    }


    @Test
    public void testConstructor4() {
        // TC04: Concave quadrangular
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {
        }
    }
    // =============== Boundary Values Tests ==================

    @Test
    public void testVertix1() {
        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testLastpoint() {
        // TC11: Last point = first point
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testCollocatedpoints() {
        // TC12: Collocated points
        try {
            new Polygon(
                    new Point3D(0, 0, 1),
                    new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0),
                    new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {
        }

    }


    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");
    }

}
