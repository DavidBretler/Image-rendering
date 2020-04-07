package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {



    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal1() {
        // ============ Equivalence Partitions Tests ==============

        Ray _r=new Ray(new Point3D(1,0,0),new Vector(1,0,0));
        Tube _t=new Cylinder(40,_r,1);

        Point3D p2=new Point3D(2,2,0);

        Vector c2=new Vector(0.0,1,0);

        assertEquals(c2,_t.getNormal(p2));
    }
    // =============== Boundary Values Tests ==================

    @Test
    public void getNormal_pointinBase() {
        Point3D p2 = new Point3D(1, 0, 0);
        Vector v3 = new Vector(new Point3D(1, 1, 1));
        Ray r2 = new Ray(p2, v3);
        Cylinder c2 = new Cylinder(1, r2, 2);


        Vector v4 = c2.getNormal(new Point3D(1, 0, 0));
        assertEquals(v4, v3.normalized());
    }
    @Test
    void get_height() {
        // ============ Equivalence Partitions Tests ==============
        Point3D p=new Point3D(4,53,3);
        Vector v=new Vector(33,3,3);
        Ray r =new Ray(p,v);
        Cylinder c=new Cylinder(20,r,9);

        assertEquals(20,c.get_height(),0);
    }
}