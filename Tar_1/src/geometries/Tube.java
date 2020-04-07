package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Tube extends RadialGeometry
{

    Ray _ray;
    /**
     * constructor for a new tube object
     *
     * @param _ray    the direction of the tube from a center point
     * radios of the tube
     */
    public Tube(double _radius, Ray _ray) {
        super(_radius);
        this._ray = new Ray(_ray);
    }

    public Tube(RadialGeometry other) {
        super(other);
    }

    public Ray get_Ray() {
        return _ray ;  }

    @Override
    public double get_radius() {
        return super.get_radius();
    }

    @Override
    public String toString() {
        return
                "ray=" + _ray +
                ", _radius=" + _radius +
                '}';
    }

    /**

     * @param point point to calculate the normal
     * @return returns normal vector to the tube using diffrent vector func
     */
    @Override
    public Vector getNormal(Point3D point) {


            //The vector from the point of the cylinder to the given point
            Point3D o = _ray.getPoint(); // at this point o = p0
            Vector v =  _ray.getDirection();

            Vector vector1 = point.substract(o);

            //We need the projection to multiply the _direction unit vector
            double projection = vector1.dotProduct(v);
            if(!isZero(projection))
            {
                // projection of P-O on the ray:
                o = o.add(v.Scale(projection));
            }

            //This vector is orthogonal to the _direction vector.
            Vector check = point.substract(o);
            return check.normalize();
        }

    }

