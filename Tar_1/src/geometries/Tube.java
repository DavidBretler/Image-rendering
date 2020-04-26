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

public class Tube extends RadialGeometry {
    Ray _ray;

    public Tube(double _radius, Ray _ray) {
        super(_radius);
        this._ray = new Ray(_ray);
    }

    public Tube(RadialGeometry other) {
        super(other);
    }

    public Ray get_Ray() {
        return this._ray;
    }

    public double get_radius() {
        return super.get_radius();
    }

    public String toString() {
        return "ray=" + this._ray + ", _radius=" + this._radius + "}";
    }

    public Vector getNormal(Point3D point) {
        Point3D o = this._ray.getPoint();
        Vector v = this._ray.getDirection();
        Vector vector1 = point.subtract(o);
        double projection = vector1.dotProduct(v);
        if (!Util.isZero(projection)) {
            o = o.add(v.scale(projection));
        }

        Vector check = point.subtract(o);
        return check.normalize();
    }

    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
