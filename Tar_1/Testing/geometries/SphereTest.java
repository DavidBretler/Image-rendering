package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test method for { @link geometries.Sphere#getNormal (primitives.Point3D) }.
 */

class SphereTest {
    /**
     * Test method for {@link geometries.Sphere#getP()}.
     */
    @Test
    public void getCenter() {
        // ============ Equivalence Partitions Tests ==============

        Sphere _s=new Sphere(2,new Point3D(1,1,1));
        assertEquals(_s.getP(),new Point3D(1,1,1));
//new
    }
    @Test
    void getNormalTest1() {
        Sphere sp = new Sphere(1.0, new Point3D(0, 0, 1));
        assertEquals(new Vector(0,0,1),sp.getNormal(new Point3D(0,0,2)));
    }

    @Test
    void getNormalTest2() {
        Sphere sp = new Sphere(1,new Point3D(0,0,1));
        assertNotEquals(new Vector(0,0,1),sp.getNormal(new Point3D(0,1,1)));
        System.out.println(sp.getNormal(new Point3D(0,1,1)));
    }
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

    @Test
    public void testFindIntersections1() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals(null,
                (Supplier<String>) sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).get_x().get() > result.get(1).get_x().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        {
            Ray _r = new Ray(new Point3D(1.5, 0, 0), new Vector(3, 1, 0));
            List<Point3D> result1 = sphere.findIntersections(_r);
            assertEquals(1, result1.size(), "Wrong number of points");

        }
        // TC04: Ray starts after the sphere (0 points)
        {
            Ray _r = new Ray(new Point3D(3, 0, 0), new Vector(3, 0, 0));
            List<Point3D> result2 = sphere.findIntersections(_r);

            assertEquals(null, result2, "Wrong number of points");
        }
    }
        @Test
        public void testFindIntersections2()
    {
            // =============== Boundary Values Tests ==================

            // **** Group: Ray's line crosses the sphere (but not the center)
            // TC11: Ray starts at sphere and goes inside (1 points)
        {
            Ray _r = new Ray(new Point3D(2, 0, 0), new Vector(-3, 0.1, 0));
            List<Point3D> result1 = sphere.findIntersections(_r);

            assertEquals(1, result1.size(), "Wrong number of points");
        }

            // TC12: Ray starts at sphere and goes outside (0 points)
        {
            Ray _r = new Ray(new Point3D(2, 0, 0), new Vector(3, 0, 0));
            List<Point3D> result2 = sphere.findIntersections(_r);

            assertEquals(null, result2, "Wrong number of points");
        }
        }

    @Test
    public void testFindIntersections3() {
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        {
            Ray _r = new Ray(new Point3D(3, 0, 0), new Vector(-3, 0, 0));
            List<Point3D> result1 = sphere.findIntersections(_r);

          assertEquals(2, result1.size(), "Wrong number of points");
        }
        // TC14: Ray starts at sphere and goes inside (1 points)
        {
            Ray _r = new Ray(new Point3D(2, 0, 0), new Vector(-3, 0, 0));
            List<Point3D> result2 = sphere.findIntersections(_r);

            assertEquals(1, result2.size(), "Wrong number of points");
        }
        // TC15: Ray starts inside (1 points)

            {
                Ray _r = new Ray(new Point3D(1.5, 0, 0), new Vector(-3, 0, 0));
                List<Point3D> result3 = sphere.findIntersections(_r);

                assertEquals(1, result3.size(), "Wrong number of points");
            }

        // TC16: Ray starts at the center (1 points)
        {
            Ray _r = new Ray(new Point3D(1, 0, 0), new Vector(-3, 0, 0));
            List<Point3D> result4 = sphere.findIntersections(_r);

            assertEquals(1, result4.size(), "Wrong number of points");
        }
        // TC17: Ray starts at sphere and goes outside (0 points)
        {
            Ray _r = new Ray(new Point3D(2, 0, 0), new Vector(3, 0, 0));
            List<Point3D> result5 = sphere.findIntersections(_r);

            assertEquals(null, result5, "Wrong number of points");
        }
        // TC18: Ray starts after sphere (0 points)
        {
            Ray _r = new Ray(new Point3D(3, 0, 0), new Vector(3, 0, 0));
            List<Point3D> result6 = sphere.findIntersections(_r);

            assertEquals(null, result6, "Wrong number of points");
        }
    }

    @Test
    public void testFindIntersections4() {
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        {
            Ray _r = new Ray(new Point3D(-1, 0, 1), new Vector(1, 0, 0));
            List<Point3D> result1 = sphere.findIntersections(_r);

            assertEquals(null, result1, "Wrong number of points");
        }
        // TC20: Ray starts at the tangent point
        {
            Ray _r = new Ray(new Point3D(1, 0, 1), new Vector(1, 0, 0));
            List<Point3D> result1 = sphere.findIntersections(_r);

            assertEquals(null, result1, "Wrong number of points");
        }
        // TC21: Ray starts after the tangent point
        {
            Ray _r = new Ray(new Point3D(2, 0, 1), new Vector(1, 0, 0));
            List<Point3D> result1 = sphere.findIntersections(_r);

            assertEquals(null, result1, "Wrong number of points");
        }
    }
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        {
            Ray _r = new Ray(new Point3D(-1, 0, 0), new Vector(0, 0, 1));
            List<Point3D> result1 = sphere.findIntersections(_r);

            assertEquals(null, result1, "Wrong number of points");
        }
    }

