//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

public class PolygonTests {
    public PolygonTests() {
    }

    @Test
    public void testConstructor1() {
        try {
            new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(-1.0D, 1.0D, 1.0D)});
        } catch (IllegalArgumentException var2) {
            Assertions.fail("Failed constructing a correct polygon");
        }

    }

    @Test
    public void testConstructor2() {
        try {
            new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(-1.0D, 1.0D, 1.0D)});
            Assertions.fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException var2) {
        }

    }

    @Test
    public void testConstructor3() {
        try {
            new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(0.0D, 2.0D, 2.0D)});
            Assertions.fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException var2) {
        }

    }

    @Test
    public void testConstructor4() {
        try {
            new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(0.5D, 0.25D, 0.5D)});
            Assertions.fail("Constructed a concave polygon");
        } catch (IllegalArgumentException var2) {
        }

    }

    @Test
    public void testVertix1() {
        try {
            new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(0.0D, 0.5D, 0.5D)});
            Assertions.fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException var2) {
        }

    }

    @Test
    public void testLastpoint() {
        try {
            new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(0.0D, 0.0D, 1.0D)});
            Assertions.fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException var2) {
        }

    }

    @Test
    public void testCollocatedpoints() {
        try {
            new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D)});
            Assertions.fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException var2) {
        }

    }

    @Test
    public void testGetNormal() {
        Polygon pl = new Polygon(new Point3D[]{new Point3D(0.0D, 0.0D, 1.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(-1.0D, 1.0D, 1.0D)});
        double sqrt3 = Math.sqrt(0.3333333333333333D);
        Assertions.assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0.0D, 0.0D, 1.0D)), "Bad normal to trinagle");
    }
}
