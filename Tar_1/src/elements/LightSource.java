package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface LightSource creates a standard light with direction.
 */
public interface LightSource {
    /**
     * getter
     * @param p
     * @return the direction of the light
     */
    Vector getL(Point3D p);

    /**
     * getter
     * @param p
     * @return the color of the light
     */
    Color getIntensity(Point3D p);

    double getDistance(Point3D pointGeo);
}
