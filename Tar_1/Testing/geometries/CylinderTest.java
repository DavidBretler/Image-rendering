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

class CylinderTest {
    CylinderTest() {
    }

    @Test
    public void getNormal1() {
        Ray _r = new Ray(new Point3D(1.0D, 0.0D, 0.0D), new Vector(1.0D, 0.0D, 0.0D));
        Tube _t = new Cylinder(40.0D, _r, 1.0D);
        Point3D p2 = new Point3D(2.0D, 2.0D, 0.0D);
        Vector c2 = new Vector(0.0D, 1.0D, 0.0D);
        Assertions.assertEquals(c2, _t.getNormal(p2));
    }

    @Test
    public void getNormal_pointinBase() {
        Point3D p2 = new Point3D(1.0D, 0.0D, 0.0D);
        Vector v3 = new Vector(new Point3D(1.0D, 1.0D, 1.0D));
        Ray r2 = new Ray(p2, v3);
        Cylinder c2 = new Cylinder(1.0D, r2, 2.0D);
        Vector v4 = c2.getNormal(new Point3D(1.0D, 0.0D, 0.0D));
        Assertions.assertEquals(v4, v3.normalized());
    }

    @Test
    void get_height() {
        Point3D p = new Point3D(4.0D, 53.0D, 3.0D);
        Vector v = new Vector(33.0D, 3.0D, 3.0D);
        Ray r = new Ray(p, v);
        Cylinder c = new Cylinder(20.0D, r, 9.0D);
        Assertions.assertEquals(20.0D, c.get_height(), 0.0D);
    }
}
