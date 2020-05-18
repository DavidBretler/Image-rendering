package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * light source that shines to a specific direction specific angle
 */
public class SpotLight extends PointLight {
     Vector _direction;

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
        super(_intensity, _position,  _kC, _kC, _kC);
        this._direction=_direction;
    }
}
