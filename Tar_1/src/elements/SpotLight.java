package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * light source that shines to a specific direction specific angle
 */
public class SpotLight extends PointLight {
     Vector _direction;
    private double _concentration;
    /**
     * constructor with _concentration
     * @param _intensity
     * @param _position of the light
     * @param _direction of the light
     * @param _kC-Constant attenuation
     * @param _kC- Linear attenuation
     * @param _kC-Quadratic attenuation
     * @_concentration
     */

    public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ, double _concentration) {
        super(_intensity, _position, _kC, _kL, _kQ);
        this._direction = new Vector(_direction).normalized();
        this._concentration = _concentration;
    }

    /**
     * constructor
     * @param _intensity
     * @param _position of the light
     * @param _direction of the light
     * @param _kC-Constant attenuation
     * @param _kC- Linear attenuation
     * @param _kC-Quadratic attenuation
     */
    public SpotLight(Color _intensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
        this(_intensity, _position, _direction, _kC, _kL, _kQ, 1);
    }


    /**
     * @return spotlight intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);

        if (_concentration != 1) {
            factor = Math.pow(factor, _concentration);
        }

        return (pointlightIntensity.scale(factor));
    }

    @Override
    public Vector getL(Point3D point) {
        return super.getL(point);
    }
}
