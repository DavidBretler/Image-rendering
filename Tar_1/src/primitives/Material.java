package primitives;

public class Material {
    double _kt;//transparency

    /**
     * consractor with all the parameters
     * @param _kD  attenuation diffuse factors
     * @param _kS   attenuation specular factors
     * @param _nShininess shininess factor
     * @param _kt trancparty factor
     * @param _kr reflaction factor
     */
    public Material( double _kD, double _kS, int _nShininess,double _kt, double _kr) {
        this._kt = _kt;
        this._kr = _kr;
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    double _kr;//reflection
    double _kD;
    double _kS;
    int _nShininess;

    /** consractor
     * @param _kD         attenuation diffuse factors
     * @param _kS         attenuation specular factors
     * @param _nShininess shininess factor
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this  (_kD,_kS,_nShininess,0,0);

    }

    /**
     * copy constructor
     * @param material
     */
    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess,material._kt,material._kr);
    }

    /**
     * @return attenuation factors
     */
    public double getKd() {
        return this._kD;
    }

    /**
     * @return attenuation factors
     */
    public double getKs() {
        return this._kS;
    }

    /**
     * @return shininess factor
     */
    public int getnShininess()
    {
        return this._nShininess;
    }

    /**
     *
     * @return trancparty factor
     */
    public double getKt() {
        return _kt;
    }

    /**
     *
     * @return reflaction factor
     */
    public double getKr() {
        return _kr;
    }
}

