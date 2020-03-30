package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @org.junit.jupiter.api.Test
    void dotProduct() {
    }

    @org.junit.jupiter.api.Test
    void crossProduct() {
    }

    @Test
    void lengthSquared() {
    }

    @Test
    void length() {
    }

    @Test
    void normalize() {
    }

    @org.junit.jupiter.api.Test
    void normalized() {
    }
}