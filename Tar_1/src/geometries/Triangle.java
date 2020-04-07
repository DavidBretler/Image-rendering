package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.util.Arrays;

/**
 * triangle is a plane with three points
 */
public class Triangle extends Polygon
{

    public Triangle(Point3D x,Point3D y,Point3D z) {
        super(x,y,z);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Triangle)) return false;

        Triangle tr = (Triangle) obj;


        return Arrays.asList(_vertices).containsAll(Arrays.asList(tr._vertices));
    }
    @Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal();
    }
}

