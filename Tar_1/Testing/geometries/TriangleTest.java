package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test method for { @link geometries.Triangle#getNormal (primitives.Point3D) }.
 */
class TriangleTest {
    // ============ Equivalence Partitions Tests ==============
    /**
     * Test method for {@link geometries.Triangle#equals(Object)}.
     */
    @Test
    public void testEquals() {
        // ============ Equivalence Partitions Tests ==============

        Triangle t= new Triangle(new Point3D(1,0,0),new Point3D(0,2,0),new Point3D(0,0,3));
        Triangle t2= new Triangle(new Point3D(1,0,0),new Point3D(0,2,0),new Point3D(0,0,3));
        assertEquals(true,t.equals(t2));
    }

    @Test
    void testGetNormal() {
        Triangle pl1 =new Triangle(
                new Point3D(0.0,0.0,1.0),
                new Point3D(0.0,1.0,0.0),
                new Point3D(1.0,0.0,0.0));
        Vector v1=pl1.getNormal(new Point3D(0,0,0));

        Triangle pl2 =new Triangle(
                new Point3D(0.0,1.0,0.0),
                new Point3D(1.0,0.0,0.0),
                new Point3D(0.0,0.0,1.0));
        Vector v2=pl2.getNormal(new Point3D(0,0,0));

        Triangle pl3 =new Triangle(
                new Point3D(1.0,0.0,0.0),
                new Point3D(0.0,1.0,0.0),
                new Point3D(0.0,0.0,1.0));
        Vector v3=pl3.getNormal(new Point3D(0,0,0));

        assertEquals(v1,v2);

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);

        assertNotEquals(v1,v3);
    }
/**
    EP: Three cases:
            • Inside polygon/triangle
• Outside against edge
• Outside against vertex
 */
Triangle Tl1 =new Triangle(
        new Point3D(-2.0,-2.0,0),
        new Point3D(0.0,1.0,0.0),
        new Point3D(1.0,0.0,0.0));
@Test
public void intersectionTest()
    // ============ Equivalence Partitions Tests ==============
    //            • Inside triangle
        {
    assertEquals(1,
            ( Tl1.findIntersections(new Ray(new Point3D(0,0,4) ,new Vector(0,0,-6)))).size(),
            "wrong number of intersections");

        }

}