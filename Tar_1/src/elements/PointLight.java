package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The PointLight object specifies an attenuated light source at a fixed point in space that radiates light equally
 * in all directions away from the light source. PointLight has the same attributes as a Light node,
 * with the addition of location and attenuation parameters.
 * <p>
 * A point light contributes to diffuse and specular reflections,
 * which in turn depend on the orientation and position of a surface.
 * A point light does not contribute to ambient reflections.
 * <p>
 * A PointLight is attenuated by multiplying the contribution of the light by an attenuation factor.
 * The attenuation factor causes the the PointLight's brightness to decrease
 * as distance from the light source increases.
 * A PointLight's attenuation factor contains three values:
 * <p>
 * Constant attenuation
 * Linear attenuation
 * Quadratic attenuation
 * <p>
 * A PointLight is attenuated by the reciprocal of the sum of:
 * <p>
 * The constant attenuation factor
 * The Linear attenuation factor times the distance between the light and the vertex being illuminated
 * The quadratic attenuation factor times the square of the distance between the light and the vertex
 * <p>
 * By default, the constant attenuation value is 1 and the other two values are 0,
 * resulting in no attenuation.
 */
public class PointLight extends Light implements LightSource {
    Point3D _position;
    double _kC; // Constant attenuation
    double _kL; // Linear attenuation
    double _kQ; // Quadratic attenuation

    public PointLight(Color colorIntensity, Point3D position, double kC, double kL, double kQ) {
        this._intensity = colorIntensity;
        this._position = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    // by default, the constant attenuation value is 1 and the other two values are 0
    public PointLight(Color colorIntensity, Point3D position) {
        this(colorIntensity, position, 1d, 0d, 0d);
    }

    //dummy overriding Light getIntensity()
    @Override
    public Color get_intensity() {
        return super.get_intensity();
    }

    //overriding LightSource getIntensity(Point3D)
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = new Vector(p,_position).lengthSquared();
        ;
        double d = p.distance(_position);

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }

    // Light vector
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();
    }
}