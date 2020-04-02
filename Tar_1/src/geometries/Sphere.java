package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    Point3D P;

    public Sphere(double _radius, Point3D _o) {
        super(_radius);
        P = _o;
    }



    @Override
    public double get_radius() {
        return super.get_radius();
    }

    public Point3D getP() {
        return P;
    }

    @Override
    public String toString() {
        return
                "P=" + P +
                ", _radius=" + _radius +
                '}';
    }

    // TODO: 02/04/2020 finsh 
    @Override
    public Vector getNormal(Point3D p) {
        return new Vector(new Point3D(_o.get));
    }
}
