package geometries;


import primitives.Point3D;
import primitives.Vector;


public class Plane implements Geometry
{
    Point3D _p;
    Vector _normal;

    public Plane(Point3D vertex_x, Point3D vertex_y, Point3D vertex_z) {
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    public Vector getNormal() {
        return new   Vector (_normal);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_p=" + _p +
                ", _normal=" + _normal +
                '}';
    }
}
