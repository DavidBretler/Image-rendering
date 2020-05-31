//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.List;
import java.util.Objects;

import primitives.Point3D;
import primitives.Ray;

/**
 * interface -the info and funcs that are conected to intercections
 */
public interface Intersectable
{
    /**
     * holds each geometry with a intersection point
     * assistant class
     */
        public static class GeoPoint {
            protected Geometry geometry;
            protected Point3D point;

        /**
         * checks if the Geopoint are the same geometry and same intersection point
         * @param o the object
         * @return true or false
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) &&
                    point.equals(geoPoint.point);
        }



        /**
         * constructor with 2 parms
         * @param geometry the geometry being intersected
         * @param targetPoint intersection point
         */
            public GeoPoint(Geometry geometry, Point3D targetPoint) {
                this.geometry=geometry;
                this.point=targetPoint;
            }

        public Geometry getGeometry() {
            return this.geometry;
        }

        public Point3D getPoint() {
                return point;
        }
    }

    /**
     * @param ray ray pointing toward a Gepmtry
     * @return List<GeoPoint> return values
     */
    default List<GeoPoint> findIntersections(Ray ray) {
        return findIntersections(ray, Double.POSITIVE_INFINITY);
    }

    List<GeoPoint> findIntersections(Ray ray, double maxDistance);


}

