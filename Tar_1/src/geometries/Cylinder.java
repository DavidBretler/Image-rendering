//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Cylinder extends Tube {
    private double _height;

    public Cylinder(double _height, Ray centerRay, double _radius) {
        super(_radius, centerRay);
        this._height = _height;
    }

    public Vector getNormal(Point3D point) {
        Point3D o = super.get_Ray().getPoint();
        Vector v = super.get_Ray().getDirection();

        double temp;
        try {
            temp = Util.alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException var7) {
            return v;
        }

        if (temp != 0.0D && !Util.isZero(this._height - temp)) {
            o = o.add(v.Scale(temp));
            return point.subtract(o).normalize();
        } else {
            return v;
        }
    }

    public double get_height() {
        return this._height;
    }

    public String toString() {
        double var10000 = this._height;
        return "Cylinder{_height=" + var10000 + "centerRay=" + super.get_Ray() + "_radius=" + super.get_radius() + "}";
    }

    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
