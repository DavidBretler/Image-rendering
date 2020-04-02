package geometries;


import primitives.Point3D;
import primitives.Vector;


public class Plane implements Geometry
{
    Point3D _p;
    Vector _normal;

    public Plane(Point3D p1, Point3D p2, Point3D p3)
    {
        Vector U=new Vector(p1,p2);
        Vector V=new Vector(p1,p3);
        Vector N=V.crossProduct(U);
        N.normalize();

        _normal=N.Scale(-1);
    }

    public Plane(Point3D _p,Vector _normal)
    {
        this._normal=new Vector(_normal);
        this._p=new Point3D(_p);
    }


     @Override
     public Vector getNormal(Point3D p) {
     return _normal;
     }


    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return
                "_p=" + _p +
                ", _normal=" + _normal +
                '}';
    }
}
