package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Triangle extends Polygon
{

    public Triangle(Point3D x,Point3D y,Point3D z) {
        super(x,y,z);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal();
    }
}

