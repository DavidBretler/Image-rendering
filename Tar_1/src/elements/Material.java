package elements;

public class Material {

        double _kD;
         double _kS;
         int _nShininess;

          public Material(double _kD, double _kS, int _nShininess) {
            this._kD = _kD;
            this._kS = _kS;
            this._nShininess = _nShininess;
        }

    public Material(Material material) {
            this(material._kD, material._kS, material._nShininess);
        }

        public double getKd() {
            return _kD;
        }

        public double getKs() {
            return _kS;
        }

        public int getnShininess() {
            return _nShininess;
        }
    }

