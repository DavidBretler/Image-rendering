package primitives;

/**
 * ray class david is stupid :) haha
 */
public class Ray
{
    Point3D _p0;
    Vector _dir;

    public Ray(Point3D _p, Vector vec)
    {
        _p0 = new Point3D(_p._x, _p._y, _p._z);
        _dir = new Vector(vec);
    }
}