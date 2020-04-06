package primitives;

/**
 * ray class
 */
public class Ray
{
    Point3D _p0;
    Vector _direction;

    /**
     *
     * @param _p
     * @param vec make sure that the vector is normlized
     */
    public Ray(Point3D _p, Vector vec)
    {
        _p0 = new Point3D(_p._x, _p._y, _p._z);
        _direction = new Vector(vec).normalize();
    }

    public Ray(Ray _ray) {
        this._p0=new Point3D(_ray.getPoint());
        this._direction=new Vector(_ray._direction);
    }

    public Point3D getPoint() {
        return _p0;
    }

    public Vector getDirection() {
        return _direction;
    }

    @Override
    public String toString() {
        return
                "_p0=" + _p0 +
                ", _dir=" + _direction
                        +
                '}';
    }
}