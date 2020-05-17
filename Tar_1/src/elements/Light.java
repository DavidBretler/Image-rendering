package elements;

import primitives.Color;

public abstract class Light
{
    public Light() {
    }

    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    protected   Color _intensity;

    public void set_intensity(Color _intensity) {
        this._intensity = _intensity;
    }

    public Color get_intensity() {
        return  new Color(_intensity);
    }
}
