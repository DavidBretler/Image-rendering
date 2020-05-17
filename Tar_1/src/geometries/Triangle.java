//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package geometries;

import java.util.Arrays;
import java.util.List;

import elements.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Polygon {

    public Triangle(Color _emission,Point3D _p1 ,Point3D _p2 ,Point3D _p3 ) {
        super(_emission,_p1,_p2,_p3);
        set_emission(_emission);
    }

    public Triangle(Point3D x, Point3D y, Point3D z) {
     super(new Point3D[]{x, y, z});
    }

    public Triangle(Color color, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(color,p1,p2,p3);
        set_emission(_emission);

    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Triangle)) {
            return false;
        } else {
            Triangle tr = (Triangle)obj;
            return Arrays.asList(this._vertices).containsAll(Arrays.asList(tr._vertices));
        }
    }

    public Vector getNormal(Point3D point) {
        return this._plane.getNormal();
    }

    public String toString() {
        return "Triangle{_vertices=" + this._vertices + "}";
    }

    public List<GeoPoint> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
