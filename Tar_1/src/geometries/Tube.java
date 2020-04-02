package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry
{
    public Tube(double _radius) {
        super(_radius);
    }
    Ray ray;
    public Tube(RadialGeometry other) {
        super(other);
    }

    public Ray getRay() {
        return ray;
    }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    @Override
    public String toString() {
        return
                "ray=" + ray +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
