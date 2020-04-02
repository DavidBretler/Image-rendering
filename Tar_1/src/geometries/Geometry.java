package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * is the coomon interface for gemetries that using the  get normal
 */
public interface Geometry
{
    Vector getNormal(Point3D p);
        }