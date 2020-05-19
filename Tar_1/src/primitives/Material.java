package primitives;

public class Material {

        double _kD;
         double _kS;
         int _nShininess;

    /**
     *
     * @param  _kD attenuation factors
     * @param _kS attenuation factors
     * @param _nShininess  shininess factor
     */
          public Material(double _kD, double _kS, int _nShininess) {
            this._kD = _kD;
            this._kS = _kS;
            this._nShininess = _nShininess;
        }

    public Material(Material material) {
            this(material._kD, material._kS, material._nShininess);
        }

    /**
     *
     * @return attenuation factors
     */
        public double getKd() {
            return this._kD;
        }
    /**
     *
     * @return attenuation factors
     */
        public double getKs() {
            return this._kS;
        }
    /**
     *
     * @return shininess factor
     */
        public int getnShininess() {
            return this._nShininess;
        }
    }

