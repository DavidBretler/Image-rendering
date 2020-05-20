package elements;

import primitives.Color;

/**
 * class AmbientLight the background light
 */
public class  AmbientLight extends Light
{

    /**
     * AmbientLight constructor
     * calculates the :intensity =Ia*Ka
     * @param  iA
     */
    public AmbientLight(Color iA, double kA)
    {
        this._intensity = iA.scale(kA);


    }

    /**
     * AmbientLight constructor
     * constructor with default val ka=1
     * @param _intensity
     */
    // TODO: 17/05/2020 check if ok 
    public AmbientLight(Color _intensity) {
    super(_intensity); 
    }

    /**
     * sets the  Intensity of color
     * @param _intensity
     */
    public void setIntensity(Color _intensity)
    {
        this._intensity = _intensity;
    }


}
