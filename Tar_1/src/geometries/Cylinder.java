package geometries;

import primitives.*;
import primitives.Vector;

import java.util.List;

/**
 *_height the hight of the Cylinder
 * _radius  of the Cylinder
 * Ray ,the ray in the center of the Cylinder
 */
public class Cylinder extends Tube  {
    private  double _height;
    public Cylinder(double _height,Ray centerRay,double _radius){
        super(_radius,centerRay);
        this._height=_height;
    }

    /**
     *
     * @param point point to calculate the normal
     * @return returns normal vector
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D o = super.get_Ray().getPoint();
        Vector v = super.get_Ray().getDirection();

        // projection  on the center ray:
        double temp;
        try {
            temp = Util.alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (temp == 0 ||Util.isZero(_height - temp)) // if it's close to 0 a ZERO vector exception will come
            return v;

        o = o.add(v.Scale(temp));
        return point.subtract(o).normalize();//calculate normall like in tube
    }
    public double get_height() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height="+_height+
                "centerRay=" + super.get_Ray() +
                "_radius="+super.get_radius()+ '}';
    }
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);

    }
}