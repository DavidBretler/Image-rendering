//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

class TriangleTest {
    Triangle Tl1 = new Triangle(new Point3D(-2.0D, -2.0D, 0.0D), new Point3D(0.0D, 2.0D, 0.0D), new Point3D(2.0D, 0.0D, 0.0D));

    TriangleTest() {
        Assertions.assertNull(this.Tl1.findIntersections(new Ray(new Point3D(3.0D, 3.0D, 4.0D), new Vector(0.0D, 0.0D, -6.0D))), "wrong number of intersections");
        Assertions.assertNull(this.Tl1.findIntersections(new Ray(new Point3D(0.0D, 3.0D, 4.0D), new Vector(0.0D, 0.0D, -6.0D))), "wrong number of intersections");
        Assertions.assertNull(this.Tl1.findIntersections(new Ray(new Point3D(1.0D, 1.0D, 4.0D), new Vector(0.0D, 0.0D, -6.0D))), "wrong number of intersections");
        Assertions.assertNull(this.Tl1.findIntersections(new Ray(new Point3D(-2.0D, -2.0D, 4.0D), new Vector(0.0D, 0.0D, -6.0D))), "wrong number of intersections");
        Assertions.assertNull(this.Tl1.findIntersections(new Ray(new Point3D(-1.0D, 3.0D, 4.0D), new Vector(0.0D, 0.0D, -6.0D))), "wrong number of intersections");
    }

    @Test
    public void testEquals() {
        Triangle t = new Triangle(new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 2.0D, 0.0D), new Point3D(0.0D, 0.0D, 3.0D));
        Triangle t2 = new Triangle(new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 2.0D, 0.0D), new Point3D(0.0D, 0.0D, 3.0D));
        Assertions.assertEquals(true, t.equals(t2));
    }

    @Test
    void testGetNormal() {
        Triangle pl1 = new Triangle(new Point3D(0.0D, 0.0D, 1.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(1.0D, 0.0D, 0.0D));
        Vector v1 = pl1.getNormal(new Point3D(0.0D, 0.0D, 0.0D));
        Triangle pl2 = new Triangle(new Point3D(0.0D, 1.0D, 0.0D), new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 0.0D, 1.0D));
        Vector v2 = pl2.getNormal(new Point3D(0.0D, 0.0D, 0.0D));
        Triangle pl3 = new Triangle(new Point3D(1.0D, 0.0D, 0.0D), new Point3D(0.0D, 1.0D, 0.0D), new Point3D(0.0D, 0.0D, 1.0D));
        Vector v3 = pl3.getNormal(new Point3D(0.0D, 0.0D, 0.0D));
        Assertions.assertEquals(v1, v2);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        Assertions.assertNotEquals(v1, v3);
    }

    @Test
    public void intersectionTest() {
        Assertions.assertEquals(1, this.Tl1.findIntersections(new Ray(new Point3D(0.0D, 0.0D, 4.0D), new Vector(0.0D, 0.0D, -6.0D))).size(), "wrong number of intersections");
    }
}
