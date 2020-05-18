package elements;

import primitives.Color;

/**
 *  abstract class Light holds the intensity of the lights
 */
 public abstract class Light
{
    protected Light() {
    }

    /**
     * constructor sets the intensity
     * @param _intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    protected   Color _intensity;

    public void set_intensity(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     *
     * @return the intensity of the light
     */
    public Color get_intensity() {
        return  new Color(_intensity);
    }
}
