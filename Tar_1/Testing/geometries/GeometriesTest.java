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

import java.io.PrintStream;

class GeometriesTest {
    Sphere _sphere = new Sphere(2.0D, new Point3D(1.0D, 0.0D, 0.0D));
    Triangle _tri = new Triangle(new Point3D(0.0D, 2.0D, 0.0D), new Point3D(2.0D, 0.0D, 0.0D), new Point3D(-2.0D, -2.0D, 0.0D));
    Ray _ray = new Ray(new Point3D(50.0D, 50.0D, 4.0D), new Vector(0.0D, 0.0D, -4.0D));

    GeometriesTest() {
    }

    @Test
    void add() {
        Geometries geo = new Geometries();
        geo.add(new Intersectable[0]);
        geo.add(new Intersectable[]{this._sphere});
        geo.add(new Intersectable[]{this._tri});
        Assertions.assertEquals(2, geo.size(), "did not add the shapes");
    }

    @Test
    void findIntersections1() {
        Sphere _sphere = new Sphere(2.0D, new Point3D(1.0D, 0.0D, 0.0D));
        Triangle _tri = new Triangle(new Point3D(0.0D, 2.0D, 0.0D), new Point3D(2.0D, 0.0D, 0.0D), new Point3D(-2.0D, -2.0D, 0.0D));
        Ray _ray = new Ray(new Point3D(50.0D, 50.0D, 4.0D), new Vector(0.0D, 0.0D, -4.0D));
        Geometries geo = new Geometries();
        geo.add(new Intersectable[]{_sphere});
        geo.add(new Intersectable[]{_tri});
        PrintStream var10000 = System.out;
        String var10001 = _sphere.toString();
        var10000.println(var10001 + _tri.toString());
        Assertions.assertNull(geo.findIntersections(_ray), "did not add the shapes");
    }

    @Test
    void findIntersections2() {
        Geometries geo1 = new Geometries();
        Assertions.assertNull(geo1.findIntersections(this._ray), "did not add the shapes");
    }

    @Test
    void findIntersections3() {
        Sphere _sphere = new Sphere(0.5D, new Point3D(1.0D, 0.0D, 0.0D));
        Triangle _tri = new Triangle(new Point3D(0.0D, 2.0D, 0.0D), new Point3D(2.0D, 0.0D, 0.0D), new Point3D(-2.0D, -2.0D, 0.0D));
        Ray _ray = new Ray(new Point3D(-1.0D, -1.0D, 4.0D), new Vector(0.0D, 0.0D, -4.0D));
        Geometries geo = new Geometries();
        geo.add(new Intersectable[]{_sphere});
        geo.add(new Intersectable[]{_tri});
        Assertions.assertEquals(1, geo.findIntersections(_ray).size(), "did not add the shapes");
    }

    @Test
    void findIntersections4() {
        Sphere _sphere = new Sphere(0.5D, new Point3D(1.0D, 0.0D, 0.0D));
        Triangle _tri = new Triangle(new Point3D(0.0D, 2.0D, 0.0D), new Point3D(2.0D, 0.0D, 0.0D), new Point3D(-2.0D, -2.0D, 0.0D));
        Sphere _sphere1 = new Sphere(4.0D, new Point3D(1.0D, 0.0D, 0.0D));
        Ray _ray = new Ray(new Point3D(-1.0D, -1.0D, 4.0D), new Vector(0.0D, 0.0D, -4.0D));
        Geometries geo = new Geometries();
        geo.add(new Intersectable[]{_sphere});
        geo.add(new Intersectable[]{_tri});
        geo.add(new Intersectable[]{_sphere1});
        Assertions.assertEquals(3, geo.findIntersections(_ray).size(), "did not add the shapes");
    }

    @Test
    void findIntersections5() {
        Sphere _sphere = new Sphere(10.0D, new Point3D(1.5D, 0.0D, 0.0D));
        Triangle _tri = new Triangle(new Point3D(0.0D, 2.0D, 0.0D), new Point3D(2.0D, 0.0D, 0.0D), new Point3D(-2.0D, -2.0D, 0.0D));
        Sphere _sphere1 = new Sphere(4.0D, new Point3D(1.0D, 0.0D, 0.0D));
        Ray _ray = new Ray(new Point3D(-1.0D, -1.0D, 4.0D), new Vector(0.0D, 0.0D, -4.0D));
        Geometries geo = new Geometries();
        geo.add(new Intersectable[]{_sphere});
        geo.add(new Intersectable[]{_tri});
        geo.add(new Intersectable[]{_sphere1});
        Assertions.assertEquals(4, geo.findIntersections(_ray).size(), "did not add the shapes");
    }

    @Test
    void findIntersections6() {
        Sphere _sphere = new Sphere(0.5D, new Point3D(1.0D, 0.0D, 0.0D));
        Triangle _tri = new Triangle(new Point3D(0.0D, 2.0D, 0.0D), new Point3D(2.0D, 0.0D, 0.0D), new Point3D(-2.0D, -2.0D, 0.0D));
        Sphere _sphere1 = new Sphere(4.0D, new Point3D(1.0D, 0.0D, 0.0D));
        Ray _ray = new Ray(new Point3D(-1.0D, -1.0D, 4.0D), new Vector(0.0D, 0.0D, -4.0D));
        Geometries geo = new Geometries();
        geo.add(new Intersectable[]{_sphere});
        geo.add(new Intersectable[]{_tri});
        geo.add(new Intersectable[]{_sphere1});
        Assertions.assertEquals(3, geo.findIntersections(_ray).size(), "did not add the shapes");
    }
}
