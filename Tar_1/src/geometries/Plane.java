package geometries;


import primitives.Point3D;
import primitives.Vector;

/**
 * p is the point normal starts
 * _normal is caluclted from the point on the plane
 */
public class Plane implements Geometry
{
    Point3D _p;
    Vector _normal;

    /**
     * crates 2 vectors from the points and calc the normal
     * @param p1 point on the plane
     * @param p2 point on the plane
     * @param p3 point on the plane
     */
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
