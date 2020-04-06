package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

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

    /**
     *
     * @param point point to calculate the normal
     * @return returns normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {


            //The vector from the point of the cylinder to the given point
            Point3D o =  ray.getPoint(); // at this point o = p0
            Vector v =  ray.getDirection();

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

