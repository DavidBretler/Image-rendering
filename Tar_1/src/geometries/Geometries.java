package geometries;

import primitives.Point3D;
import primitives.Ray;

import javax.print.DocFlavor;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> _geometries = new LinkedList<>();

    public Geometries(Intersectable... _geometries) {
        add(_geometries);
    }
    public int getSize()
    {
        return _geometries.size();
    }
    public void add(Intersectable... geometries) {
//        for (Intersectable geo : geometries) {
//            _geometries.add(geo);
//        }
        _geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * Na le Hasbir befrotrot
     *
     * @param ray the ray that intersect the geometries
     * @return list of Point3D that intersect the osef
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = null;

        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray, maxDistance);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;

    }
    public void removeAll(Intersectable... geometries)
    {
        for (Intersectable intersectable:geometries)
            _geometries.remove(intersectable);
    }
    public void remove(Intersectable... intersectables) {
        for (Intersectable geo : _geometries) {
            _geometries.remove(geo);
        }
    }
}