package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {
    Vector getL(Point3D p);
    Color getIntensity(Point3D p);

}
