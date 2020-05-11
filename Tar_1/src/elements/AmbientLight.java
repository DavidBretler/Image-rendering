package elements;

import primitives.Color;

/**
 * class AmbientLight the background light
 */
public class AmbientLight {



    Color _intensity;
    double kA;

    /**
     * AmbientLight constructor
     * @param _intensity
     */
    public AmbientLight(Color _intensity, double kA) {
       // this.kA = kA;
        this._intensity = _intensity.scale(kA);

    }

    /**
     * AmbientLight constructor
     * constructor with default val ka=1
     * @param _intensity
     */
    public AmbientLight(Color _intensity) {
        this._intensity = _intensity;
        this.kA = 1.0;
    }

    /**
     * sets the  Intensity of color
     * @param _intensity
     */
    public void setIntensity(Color _intensity)
    {
        this._intensity = _intensity;
    }

    /**
     * returns the intensity of the ambient light
     * @return
     */
    public java.awt.Color getIntensity()
    {
        return _intensity.getColor();
    }

}
