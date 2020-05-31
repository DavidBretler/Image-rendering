//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.List;

import primitives.Material;
import primitives.*;

import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry {
    Point3D _center;

    public Sphere(double _radius, Point3D _o) {
        super(_radius);
        this._center = _o;
    }

    /**
     * constructor with color and material
     *
     * @param emissionLight color
     * @param material      the material pf the geometry
     * @param radius
     * @param _center
     */
    public Sphere(Color emissionLight, Material material, double radius, Point3D _center) {
        this(radius, _center);
        set_emission(emissionLight);
        this._material = material;
    }

    public double get_radius() {
        return super.get_radius();
    }

    public Point3D getP() {
        return this._center;
    }

    public String toString() {
        return "P=" + this._center + ", _radius=" + this._radius + "}";
    }

    public Vector getNormal(Point3D point) {
        Vector normal = point.subtract(this._center);
        return normal.normalize();
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException e) {
            GeoPoint geo = new GeoPoint(this, ray.getTargetPoint(_radius));

            return List.of(geo);
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = tm == 0 ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0) return null;
        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        double t1dist = alignZero(maxDistance - t1); //for the shade if the distance is to big ignore
        double t2dist = alignZero(maxDistance - t2);

        if (t1 <= 0 && t2 <= 0) {
            return null;
        }

        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) {
            GeoPoint geo1 = new GeoPoint(this, ray.getTargetPoint(t1));
            GeoPoint geo2 = new GeoPoint(this, ray.getTargetPoint(t2));
            return List.of(geo1, geo2);
        }
        if (t1 > 0)
            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)));
        else
            return List.of(new GeoPoint(this, ray.getTargetPoint(t2)));
    }

}
