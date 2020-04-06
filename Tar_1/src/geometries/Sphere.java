package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    Point3D o;

    public Sphere(double _radius, Point3D _o) {
        super(_radius);
        o = _o;
    }



    @Override
    public double get_radius() {
        return super.get_radius();
    }

    public Point3D getP() {
        return o;
    }

    @Override
    public String toString() {
        return
                "P=" + o +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * get the normal to this sphere in a given point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = point.substract(o);
        return normal.normalize();
    }

}
