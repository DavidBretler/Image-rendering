package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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
    }