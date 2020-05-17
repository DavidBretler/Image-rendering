package geometries;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;


public abstract class Geometry implements Intersectable
{
    Color _emission=new Color(java.awt.Color.WHITE);
    Material _material;
   public abstract Vector getNormal(Point3D p);

    public void set_emission(Color _emission) {
        this._emission = _emission;
    }

    public Color get_emission() {
        return _emission;
    }
}