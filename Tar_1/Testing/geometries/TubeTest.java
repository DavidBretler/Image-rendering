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

class TubeTest {
    TubeTest() {
    }

    @Test
    public void get_ray() {
        Ray _r = new Ray(new Point3D(1.0D, 1.0D, 1.0D), new Vector(1.0D, 2.0D, 3.0D));
        Assertions.assertEquals(new Point3D(1.0D, 1.0D, 1.0D), _r.getPoint());
    }

    @Test
    public void getNormal() {
        Ray _r = new Ray(new Point3D(1.0D, 0.0D, 0.0D), new Vector(1.0D, 0.0D, 0.0D));
        Tube _t = new Tube(1.0D, _r);
        Point3D p2 = new Point3D(2.0D, 2.0D, 0.0D);
        Vector c2 = new Vector(0.0D, 1.0D, 0.0D);
        Assertions.assertEquals(c2, _t.getNormal(p2));
    }
}
