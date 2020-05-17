//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public interface Intersectable
{

        public static class GeoPoint {
            public Geometry geometry;
            public Point3D point;


            public GeoPoint(Geometry geometry, Point3D targetPoint) {
                this.geometry=geometry;
                this.point=targetPoint;
            }
        }
        List<GeoPoint> findIntersections(Ray ray);

    }

