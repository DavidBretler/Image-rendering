package geometries;

import primitives.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Geometry is a abstract class witch holds the color and material of shapes
 */
public abstract class Geometry implements Intersectable
{

   protected Color _emission=new Color(java.awt.Color.WHITE);
    protected Material _material;

    /**
     *
     * @return the _material of the geometry
     */
    public Material get_material() {
        return this._material;
    }


    /**
     * constructor
     * @param _emission color of the shape
     * @param _material the material of the shape
     */
    public Geometry(Color _emission, Material _material) {
        this._emission = _emission;
        this._material = _material;
    }

    public abstract Vector getNormal(Point3D p);

    /**
     *  constructor -default color black
     */
    public Geometry()
    {
        _emission= Color.BLACK;
    }

    /**
     * constructor
     * @param _emission
     */
    public Geometry(Color _emission) {
        this._emission = _emission;
    }

    public void set_emission(Color _emission) {
        this._emission = _emission;
    }

    /**
     *
     * @return the color of the geometry
     */
    public Color get_emission() {
        return _emission;
    }
}