//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {
    private List<Intersectable> _geometries = new LinkedList();

    public Geometries(Intersectable... geometries) {
        this._geometries = this._geometries;
    }

    public Geometries() {
    }

    public void add(Intersectable... geometries) {
        Intersectable[] var2 = geometries;
        int var3 = geometries.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Intersectable geo = var2[var4];
            this._geometries.add(geo);
        }

    }

    public int size() {
        return this._geometries.size();
    }

    public List<Point3D> findIntersections(Ray _ray) {
        List<Point3D> _intersectionList = new LinkedList();
        Iterator var3 = this._geometries.iterator();

        while(true) {
            List _tempPoints;
            do {
                if (!var3.hasNext()) {
                    if (_intersectionList.size() == 0) {
                        return null;
                    }

                    return _intersectionList;
                }

                Intersectable shape = (Intersectable)var3.next();
                _tempPoints = shape.findIntersections(_ray);
            } while(_tempPoints == null);

            Iterator var6 = _tempPoints.iterator();

            while(var6.hasNext()) {
                Point3D _pt = (Point3D)var6.next();
                _intersectionList.add(_pt);
            }
        }
    }
}
