package primitives;

/**
 * basic coordinate in 3 dimentinon
 */

/**
 *
 */
public class Point3D {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    /**
     * a con
     */
    public final static Point3D ZERO =new Point3D(0.0, 0.0, 0.0);

    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this._x = _x;
        this._y = _y;
        this._z = _z;
    }
    public Point3D(Point3D _p)
    {
        this._x = _p._x;
        this._y = _p._y;
        this._z = _p._z;
    }
    public Point3D(double _x, double _y, double _z) {
        this( new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
    }

    public Point3D() {

    }


    /**
     *
     * @return new coordinate based on x
     */
    public Coordinate get_x() {
        return new Coordinate(_x);
    }

    /**
     *
     * @return new coordinate based on y
     */
    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    /**
     *
     * @return new coordinate based on z
     */
    public Coordinate get_z() {
        return new Coordinate(_z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return _x.equals(point3D._x) &&
                _y.equals(point3D._y) &&
                _z.equals(point3D._z);
    }

    /**adds the vector to a point
     *
     * @param vec
     * @return the new point
     */
    public Point3D add(Vector vec)
    {
       return new Point3D(_x.get()+vec._head.get_x().get(), _y.get()+vec._head.get_y().get() , _z.get()+vec._head.get_z().get() );
    }


        public Vector subtract(Point3D  p )
        {
            return new Vector( new Point3D(
                  this.get_x().get() -  p.get_x().get(),
                   this.get_y().get() - p.get_y().get(),
                   this.get_z().get() - p.get_z().get()
            )
            );
        }

    @Override
    public String toString() {
        return
                "(" + _x +
                ", " + _y +
                ", " + _z +
                ')';
    }

    public double distance(Point3D pt) {
        return (new Vector(this.subtract(pt)).length());
    }
}

