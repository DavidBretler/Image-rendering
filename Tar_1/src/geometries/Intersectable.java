//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public interface Intersectable {
  List<Point3D> findIntersections(Ray var1);
}
