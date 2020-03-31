package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

class VectorTest {

    @org.junit.jupiter.api.Test
    void add() {
        Vector v1 = new Vector(0.0,1.0,1.0);
        Vector v2 = new Vector(0.0,1.0,1.0);
        Vector v3 = new Vector(v1.add(v2));
        assertEquals (new Vector(0.0,2.0,2.0),v3);

    }

    @org.junit.jupiter.api.Test
    void subtract() {
    }

    @org.junit.jupiter.api.Test
    void scale() {
        Vector v1=new Vector(1.0,1.0,1.0);
        Vector vec=v1.Scale(1);
        assertEquals(vec,v1);

        vec=v1.Scale(2);
        assertEquals(vec,new Vector (2.0,2.0,2.0) );

        vec=vec.Scale(-2);
        assertEquals(vec,new Vector (-4.0,-4.0,-4.0) );

    }

    // =============== Boundary Values Tests ==================
    @Test
     void zeroVector()
        { // test zero vector
        new Vector(0, 0, 0);

    }
    @Test
    void lengthVector() {
        Vector v1 = new Vector(1, 1, 1);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // test length..
        if (!isZero(v1.lengthSquared() - 3))
            out.println("ERROR: lengthSquared() wrong value");
        if (!isZero(new Vector(1, 2, 2).length() - 3))
            out.println("ERROR: length() wrong value");
    }
    @Test
    void DotProductVector() {
        Vector v1 = new Vector(1, 2, 2);
        Vector v2 = new Vector(-2, -4, 5);
        Vector v3 = new Vector(0, 2, -2);
        // test Dot-Product
        assertEquals(v1.dotProduct(v3),0.0,"ERROR: dotProduct() wrong value");

        assertEquals(v1.dotProduct(v2) ,0.0,"ERROR: dotProduct() wrong value");

    }
    @Test
    void CrossProductVector() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        // test Cross-Product  13.49

        //assertNotEquals( v1.crossProduct(v2),0.0,("ERROR: crossProduct() for parallel vectors does not throw an exception"));


        Vector vr = v1.crossProduct(v3);

        assertEquals(0.0,alignZero(vr.length() -(v1.length() * v3.length())),"ERROR: crossProduct() wrong result length");

        assertEquals(0.0 ,vr.dotProduct(v1) ,"ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0.0,vr.dotProduct(v3),"ERROR: crossProduct() result is not orthogonal to its operands");


    }

    /**
    // test vector normalization vs vector length and cross-product
     */
    @Test
    void normalizeVector() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            out.println("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        if (u == v)
            out.println("ERROR: normalizated() function does not create a new vector");

    }
}