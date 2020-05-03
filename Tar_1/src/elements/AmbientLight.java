package elements;

import primitives.Color;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

public class AmbientLight {

    public AmbientLight(Color _intensity, double ka) {
        this._intensity = _intensity;
        this.ka = ka;
    }

    public void setIntensity(Color _intensity) {
        this._intensity = _intensity;
    }

    public java.awt.Color getIntensity() {
        return _intensity.getColor();
    }

    Color _intensity;
    double ka;
}
