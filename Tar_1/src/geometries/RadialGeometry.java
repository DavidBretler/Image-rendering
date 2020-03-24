package geometries;

abstract public class RadialGeometry
{
double _radius;

    public double get_radius() {
        return _radius;
    }


    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

    public RadialGeometry(RadialGeometry  other) {
        this._radius = other._radius;
    }
    @Override
    public String toString() {
        return "RadialGeometry{" +
                "_radius=" + _radius +
                '}';
    }
}
