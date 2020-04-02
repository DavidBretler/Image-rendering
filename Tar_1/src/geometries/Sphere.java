package geometries;

import primitives.Point3D;

public class Sphere extends RadialGeometry {
    Point3D P;

    public Sphere(double _radius, Point3D other) {
        super(_radius);
        P = other;
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
}
