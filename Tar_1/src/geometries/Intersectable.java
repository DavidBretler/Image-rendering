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


    public static class GeoPoint
    {
      Geometry _geometry;
      Point3D point;

        public Geometry getGeometry() {
            return _geometry;
        }

        public Point3D getPoint() {
            return point;
        }

        public GeoPoint(Geometry _geometry, Point3D pt)
      {
        this._geometry = _geometry;
        point = pt;
      }
    }
  List<Point3D> findIntersections(Ray var1);
}
