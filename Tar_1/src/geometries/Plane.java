package geometries;


import primitives.Point3D;
import primitives.Vector;


public class Plane implements Geometry
{
    Point3D _p;
    Vector _normal;

    public Plane(Point3D vertex_x, Point3D vertex_y, Point3D vertex_z)
    {
        _p=new Point3D( vertex_x);
        _normal=null;
        // TODO: 24/03/2020 o	בנאי עם 3 נקודות אמור לחשב את הנורמל לפי מה שנלמד על נורמל למשולש – בשלב הזה יישמר ערך null משדה הנורמל (המימוש המלא יתבצע בשלב הבא), כמו כן הבנאי ישמור את אחת הנקודות כנקודת הייחוס של המישור
    }

    public Plane(Point3D _p,Vector _normal)
    {
        this._normal=new Vector(_normal);
        this._p=new Point3D(_p);
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    public Vector getNormal() {
        return null;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_p=" + _p +
                ", _normal=" + _normal +
                '}';
    }
}
