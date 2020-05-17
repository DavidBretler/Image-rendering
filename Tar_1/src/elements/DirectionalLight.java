package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
    Vector _direction;


    public DirectionalLight(Color _intensity, Vector _direction) {
    this._intensity=_intensity;
        _direction = _direction.normalized();

    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.get_intensity();

    }


}
