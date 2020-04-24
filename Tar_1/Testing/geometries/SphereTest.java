//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

class SphereTest {
    Sphere sphere = new Sphere(1.0D, new Point3D(1.0D, 0.0D, 0.0D));

    SphereTest() {
        Ray _r = new Ray(new Point3D(-1.0D, 0.0D, 0.0D), new Vector(0.0D, 0.0D, 1.0D));
        List<Point3D> result1 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result1, "Wrong number of points");
    }

    @Test
    public void getCenter() {
        Sphere _s = new Sphere(2.0D, new Point3D(1.0D, 1.0D, 1.0D));
        Assertions.assertEquals(_s.getP(), new Point3D(1.0D, 1.0D, 1.0D));
    }

    @Test
    void getNormalTest1() {
        Sphere sp = new Sphere(1.0D, new Point3D(0.0D, 0.0D, 1.0D));
        Assertions.assertEquals(new Vector(0.0D, 0.0D, 1.0D), sp.getNormal(new Point3D(0.0D, 0.0D, 2.0D)));
    }

    @Test
    void getNormalTest2() {
        Sphere sp = new Sphere(1.0D, new Point3D(0.0D, 0.0D, 1.0D));
        Assertions.assertNotEquals(new Vector(0.0D, 0.0D, 1.0D), sp.getNormal(new Point3D(0.0D, 1.0D, 1.0D)));
        System.out.println(sp.getNormal(new Point3D(0.0D, 1.0D, 1.0D)));
    }

    @Test
    public void testFindIntersections1() {
        Assertions.assertEquals((Object)null, (Supplier)this.sphere.findIntersections(new Ray(new Point3D(-1.0D, 0.0D, 0.0D), new Vector(1.0D, 1.0D, 0.0D))), "Ray's line out of sphere");
        Point3D p1 = new Point3D(0.0651530771650466D, 0.355051025721682D, 0.0D);
        Point3D p2 = new Point3D(1.53484692283495D, 0.844948974278318D, 0.0D);
        List<Point3D> result = this.sphere.findIntersections(new Ray(new Point3D(-1.0D, 0.0D, 0.0D), new Vector(3.0D, 1.0D, 0.0D)));
        Assertions.assertEquals(2, result.size(), "Wrong number of points");
        if (((Point3D)result.get(0)).get_x().get() > ((Point3D)result.get(1)).get_x().get()) {
            result = List.of((Point3D)result.get(1), (Point3D)result.get(0));
        }

        Assertions.assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
        Ray _r = new Ray(new Point3D(1.5D, 0.0D, 0.0D), new Vector(3.0D, 1.0D, 0.0D));
        List<Point3D> result2 = this.sphere.findIntersections(_r);
        Assertions.assertEquals(1, result2.size(), "Wrong number of points");
        _r = new Ray(new Point3D(3.0D, 0.0D, 0.0D), new Vector(3.0D, 0.0D, 0.0D));
        result2 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result2, "Wrong number of points");
    }

    @Test
    public void testFindIntersections2() {
        Ray _r = new Ray(new Point3D(2.0D, 0.0D, 0.0D), new Vector(-3.0D, 0.1D, 0.0D));
        List<Point3D> result2 = this.sphere.findIntersections(_r);
        Assertions.assertEquals(1, result2.size(), "Wrong number of points");
        _r = new Ray(new Point3D(2.0D, 0.0D, 0.0D), new Vector(3.0D, 0.0D, 0.0D));
        result2 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result2, "Wrong number of points");
    }

    @Test
    public void testFindIntersections3() {
        Ray _r = new Ray(new Point3D(3.0D, 0.0D, 0.0D), new Vector(-3.0D, 0.0D, 0.0D));
        List<Point3D> result6 = this.sphere.findIntersections(_r);
        Assertions.assertEquals(2, result6.size(), "Wrong number of points");
        _r = new Ray(new Point3D(2.0D, 0.0D, 0.0D), new Vector(-3.0D, 0.0D, 0.0D));
        result6 = this.sphere.findIntersections(_r);
        Assertions.assertEquals(1, result6.size(), "Wrong number of points");
        _r = new Ray(new Point3D(1.5D, 0.0D, 0.0D), new Vector(-3.0D, 0.0D, 0.0D));
        result6 = this.sphere.findIntersections(_r);
        Assertions.assertEquals(1, result6.size(), "Wrong number of points");
        _r = new Ray(new Point3D(1.0D, 0.0D, 0.0D), new Vector(-3.0D, 0.0D, 0.0D));
        result6 = this.sphere.findIntersections(_r);
        Assertions.assertEquals(1, result6.size(), "Wrong number of points");
        _r = new Ray(new Point3D(2.0D, 0.0D, 0.0D), new Vector(3.0D, 0.0D, 0.0D));
        result6 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result6, "Wrong number of points");
        _r = new Ray(new Point3D(3.0D, 0.0D, 0.0D), new Vector(3.0D, 0.0D, 0.0D));
        result6 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result6, "Wrong number of points");
    }

    @Test
    public void testFindIntersections4() {
        Ray _r = new Ray(new Point3D(-1.0D, 0.0D, 1.0D), new Vector(1.0D, 0.0D, 0.0D));
        List<Point3D> result1 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result1, "Wrong number of points");
        _r = new Ray(new Point3D(1.0D, 0.0D, 1.0D), new Vector(1.0D, 0.0D, 0.0D));
        result1 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result1, "Wrong number of points");
        _r = new Ray(new Point3D(2.0D, 0.0D, 1.0D), new Vector(1.0D, 0.0D, 0.0D));
        result1 = this.sphere.findIntersections(_r);
        Assertions.assertEquals((Object)null, result1, "Wrong number of points");
    }
}
