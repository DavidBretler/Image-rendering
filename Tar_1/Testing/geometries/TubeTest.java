package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     * Test method for {@link Tube#get_Ray()} .
     */
    @Test
    public void get_ray() {
        // ============ Equivalence Partitions Tests ==============

        Ray _r=new Ray(new Point3D(1,1,1),new Vector(1,2,3));

        assertEquals(new Point3D(1,1,1),_r.getPoint());
    }


    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        Ray _r=new Ray(new Point3D(1,0,0),new Vector(1,0,0));
        Tube _t=new Tube(1,_r);

        Point3D p2=new Point3D(2,2,0);

        Vector c2=new Vector(0.0,1,0);

        assertEquals(c2,_t.getNormal(p2));
    }
}