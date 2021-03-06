package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * light source from a specific point
 * kC-Constant attenuation
 * kL- Linear attenuation
 * kQ-Quadratic attenuation
 */
public class PointLight extends Light implements LightSource {
  protected   Point3D _positionOfLight;
//public double radius;
    protected  double _kC;
    protected double _kL;
    protected double _kQ;

    public PointLight() {

    }


    /**
     * constructor
     * @param colorIntensity
     * @param position of the light
     * @param kC-Constant attenuation
     * @param kL- Linear attenuation
     * @param kQ-Quadratic attenuation
     */
    public PointLight(Color colorIntensity, Point3D position, double kC, double kL, double kQ) {
        this._intensity = colorIntensity;
        this._positionOfLight = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     *     by default, the constant attenuation
     *     value is 1 and the other two values are 0
      */
    public PointLight(Color colorIntensity, Point3D position) {
        this(colorIntensity, position, 1d, 0d, 0d);
    }



    /**
     *     overriding Light getIntensity()
     */
    @Override
    public Color get_intensity() {
        return super.get_intensity();
    }

    /**
     *     overriding LightSource getIntensity(Point3D)
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = new Vector(p, _positionOfLight).lengthSquared();
        ;
        double d = p.distance(_positionOfLight);

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }


    @Override
    public double getDistance(Point3D pointGeo) {
        return _positionOfLight.distance(pointGeo);
    }

    /**
     *
      * @param point
     * @return the vector from the light source to the point given
     */
    @Override
    public Vector getLightDirection(Point3D point) {
        if (point.equals(_positionOfLight)) {
            return null;
        }
        return point.subtract(_positionOfLight).normalize();
    }

    public Point3D get_positionOfLight() {
        return _positionOfLight;
    }


}