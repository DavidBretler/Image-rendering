package primitives;

public class Material {
    double _kt;//transparency

    public Material( double _kD, double _kS, int _nShininess,double _kt, double _kr) {
        this._kt = _kt;
        this._kr = _kr;
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    public double get_kD() {
        return _kD;
    }

    public double get_kS() {
        return _kS;
    }

    public double get_kt() {
        return _kt;
    }

    public double get_kr() {
        return _kr;
    }

    double _kr;//reflection
    double _kD;
    double _kS;
    int _nShininess;

    /**
     * @param _kD         attenuation diffuse factors
     * @param _kS         attenuation specular factors
     * @param _nShininess shininess factor
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this  (_kD,_kS,_nShininess,1,1);

    }

    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess);
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
    public int getnShininess() {
        return this._nShininess;
    }
}

