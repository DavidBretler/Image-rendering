package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Arrays;
import java.util.List;

import static primitives.Util.isZero;

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

    public List<Point3D> findIntersections(Ray ray)
    {// TODO: 17/04/2020  
        // use the polygon findIntersections func (need to make sure its good)
       //return super.findIntersections(ray) ;
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;
    }
}

