package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable
{

private List<Intersectable> _geometries ;

    public Geometries(Intersectable...  geometries)
    {
        this._geometries = new ArrayList<Intersectable>();
        this._geometries = _geometries;
    }

    public Geometries()
    {
        this._geometries = new ArrayList<Intersectable>();
    }
    public void add(Intersectable... geometries)
    {
        for (Intersectable geo : geometries )

            _geometries.add(geo);

    }


    @Override
    public List<Point3D> findIntersections(Ray ray)
    {
        return null;
    }


}
