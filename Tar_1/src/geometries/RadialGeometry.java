//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import primitives.Color;
import primitives.Material;

public abstract class RadialGeometry extends Geometry {
    double _radius;

    public RadialGeometry(Color emissionLight, double radius, Material material) {
    }

    public double get_radius() {
        return this._radius;
    }

    public RadialGeometry(double _radius) {
        this._radius = _radius;

    }

    public RadialGeometry(RadialGeometry other) {
        this._radius = other._radius;
    }

    public String toString() {
        return "_radius=" + this._radius + "}";
    }
}
