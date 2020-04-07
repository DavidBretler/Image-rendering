package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * center is midell of the sphere
 * radius is the radius of the sphere
 */
public class Sphere extends RadialGeometry {
    Point3D center;

    public Sphere(double _radius, Point3D _o) {
        super(_radius);
        center = _o;
    }



    @Override
    public double get_radius() {
        return super.get_radius();
    }

    public Point3D getP() {
        return center;
    }

    @Override
    public String toString() {
        return
                "P=" + center +
                ", _radius=" + _radius +
                '}';
    }

    /**
     * get the normal to this sphere in a given point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = point.substract(center);
        return normal.normalize();
    }

}
