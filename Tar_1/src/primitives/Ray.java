package primitives;

/**
 * ray class
 */
public class Ray
{
    Point3D _p0;
    Vector _dir;

    /**
     *
     * @param _p
     * @param vec make sure that the vector is normlized
     */
    public Ray(Point3D _p, Vector vec)
    {
        _p0 = new Point3D(_p._x, _p._y, _p._z);
        _dir = new Vector(vec).normalize();
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_p0=" + _p0 +
                ", _dir=" + _dir +
                '}';
    }
}