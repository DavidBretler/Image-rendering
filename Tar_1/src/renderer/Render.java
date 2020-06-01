package renderer;
// TODO: 31/05/2020 refactor
import elements.*;
import geometries.*;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class Render {

    private static final  double DELTA=0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 50; //the max recrusiv calc
    private static final double MIN_CALC_COLOR_K = 0.000000000000001;


    private Scene _scene;
    private ImageWriter _imageWriter;

    public Render(Scene _scene) {
        this._scene = _scene;
    }

    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    public Scene get_scene() {
        return _scene;
    }

    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint,ray ).getColor();
                    _imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }
    }

    /**
     * Finding the closest point to the P0 of the camera.
     *
     * @param intersectionPoints list of points, the function should find from
     *                           this list the closet point to P0 of the camera in the scene.
     * @return the closest point to the camera
     */

    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        GeoPoint result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();

        for (GeoPoint geo : intersectionPoints) {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist) {
                mindist = distance;
                result = geo;
            }
        }
        return result;
    }

    /**
     * Printing the grid with a fixed interval between lines
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color colorsep) {
        double rows = this._imageWriter.getNy();
        double collumns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int collumn = 0; collumn < collumns; ++collumn) {
                if (collumn % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(collumn, row, colorsep);
                }
            }
        }
    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }



/*    *//**
     *
     * @param inRay
     * @return the closest intersection point of the ray with a geometry in the scene
     *//*
    private GeoPoint findClosestIntersection(Ray inRay) {
       if (inRay==null) return null ;
        List<GeoPoint> intersectionPoints = get_scene().getGeometries().findIntersections(inRay);
        if (intersectionPoints == null) {
            return null;
        } else {
            return getClosestPoint(intersectionPoints);
        }
    }*/
    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     *
     * @param ray intersecting the scene
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {

        if (ray == null) {
            return null;
        }

        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.getPoint();

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
        if (intersections == null)
            return null;

        for (GeoPoint geoPoint : intersections) {
            double distance = ray_p0.distance(geoPoint.getPoint());
            if (distance < closestDistance) {
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }

    /**
     * the entail call to calc color of the pixel
     * @param geoPoint the shape and point
     * @param inRay the ray intersecting the point
     * @return
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        GeoPoint ClosesPoint =findClosestIntersection(inRay);
        Color resultColor =_scene.getAmbientLight().get_intensity();
        resultColor=resultColor.add(calcColor(ClosesPoint,inRay,MAX_CALC_COLOR_LEVEL,1.0));

        return resultColor;
    }
    /**
     *
     * @param geoPoint the inter point
     * @param inRay the ray from the light source
     * @param level the depth of the recursive of the transparency and the reflection
     * @param k min transparency and reflection level we take in to count
     * @return final color
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k)
    {
        if (level == 1 || k < MIN_CALC_COLOR_K) //stop condition
        {
            return Color.BLACK; //0,0,0
        }

        Color result = geoPoint.getGeometry().get_emission();//original color of geometry
        Point3D pointGeo = geoPoint.getPoint();

        Vector vecGeoCamera = pointGeo.subtract(_scene.getCamera().get_p0()).normalize();//vector from geometry to camera
        Vector normal = geoPoint.getGeometry().getNormal(pointGeo);

        double DP_Normal_vecGeoCamera = alignZero(normal.dotProduct(vecGeoCamera));
        if (DP_Normal_vecGeoCamera == 0) {
            //ray parallel to geometry surface ??
            //and orthogonal to normal
            return result;
        }

        Material material = geoPoint.getGeometry().get_material();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        double kr = geoPoint.getGeometry().get_material().getKr();
        double kt = geoPoint.getGeometry().get_material().getKt();
        double kkr = k * kr;
        double kkt = k * kt;

        result = result.add(getLightSourcesColors(geoPoint, k, result, vecGeoCamera, normal, DP_Normal_vecGeoCamera, nShininess, kd, ks));

        if (kkr > MIN_CALC_COLOR_K) { //start reflected recursiv
            Ray reflectedRay = constructReflectedRay(normal, pointGeo, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                result = result.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        if (kkt > MIN_CALC_COLOR_K) {//start transparency recursiv
            Ray refractedRay = constructRefractedRay(pointGeo, inRay, normal);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                result = result.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return result;
    }

    /**
     *
     * @param geoPoint
     * @param k
     * @param result
     * @param v
     * @param n
     * @param nv
     * @param nShininess
     * @param kd
     * @param ks
     * @return
     */
    private Color getLightSourcesColors(GeoPoint geoPoint, double k, Color result, Vector v, Vector n, double nv, int nShininess, double kd, double ks) {
        Point3D pointGeo = geoPoint.getPoint();
        List<LightSource> lightSources = _scene.get_lights();
        if (lightSources != null) {
            for (LightSource lightSource : lightSources) {
                Vector l = lightSource.getLightDirection(pointGeo);
                double DP_n_l = alignZero(n.dotProduct(l));
                if (DP_n_l * nv > 0) {
//                if (sign(DP_n_l) == sign(nv) && DP_n_l != 0 && nv != 0) {
//                    if (unshaded(lightSource, l, n, geoPoint)) {

                    double ktr = transparency(lightSource, l, n, geoPoint);
                    if (ktr * k > MIN_CALC_COLOR_K) {
                        Color ip = lightSource.getIntensity(pointGeo).scale(ktr);
                        result = result.add(
                                calcDiffusive(kd, DP_n_l, ip),
                                calcSpecular(ks, l, n, DP_n_l, v, nShininess, ip));
                    }
                }
            }
        }
        return result;
    }

    /**
     *
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);//ray with adjusment
        Point3D pointGeo = geopoint.getPoint();

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return 1d;
        }
        double lightDistance = light.getDistance(pointGeo);
        double ktr = 1d;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0)
            {
                ktr *= gp.getGeometry().get_material().getKt();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0.0;
                }
            }
        }
        return ktr;
    }
    /**
     * calc the reflection direction
     * @param normal
     * @param point
     * @param inRay
     * @return reflected ray
     */
    private Ray constructReflectedRay (Vector normal,Point3D point,Ray inRay) {//r=v-2*v*n*n
        Vector v = inRay.getDirection();
        double vn = v.dotProduct(normal);
        if (vn == 0)
        {
            return null;  }
        Vector r = v.add(normal.scale(-2 * vn));

        return new Ray(point, r);
    }
    /**
     * calc the refracted ray
     * @param point hit point
     * @param inRay the ray towards the shape
     * @return new refracted ray
     */
    private Ray constructRefractedRay (Point3D point,Ray inRay,Vector n)
    {
        return new Ray(point ,inRay.getDirection(),n);
    }




    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component coef
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param v          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     * @author Dan Zilberstein
     * <p>
     * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
     * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
     * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
     * the surface.
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        double p = nShininess;

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        return ip.scale(ks * Math.pow(minusVR, p));
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     * @author Dan Zilberstein
     * <p>
     * The diffuse component is that dot product n•L that we discussed in class. It approximates light, originally
     * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
     * surface is paper. In general, you'll also want this to have a non-gray color value, so this term would in general
     * be a color defined as: [rd,gd,bd](n•L)
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) nl = -nl;
        return ip.scale(nl * kd);
    }

}
/*
package renderer;


import elements.*;
import geometries.*;
import primitives.*;
import scene.Scene;

//import java.awt.*;
import java.util.List;

import static primitives.Util.alignZero;

*/
/**
 * renders a image form the scene and saves as jpeg file
 *//*

public class Render
{

    private static final  double DELTA=0.1;
    ImageWriter _imageWriter;
    Scene _scene;




    public Render(ImageWriter _ImageWriter, Scene _Scene) {
        this._imageWriter = _ImageWriter;
        this._scene = _Scene;
    }

    public void renderImage()

    {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera= _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double  distance = _scene.getDistance();

        */
/**
        width and height are the number of pixels in the rows
        //and columns of the view plane
        *//*

        double width = (double) _imageWriter.getWidth();
        double height = (double) _imageWriter.getHeight();

        */
/**
         * Nx and Ny are the width and height of the image.
         *//*

        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        Ray ray;
        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                ray = camera.constructRayThroughPixel(Nx, Ny,column,row, distance, width, height);

                 List<Intersectable.GeoPoint> intersectionPoints = _scene.getGeometries().findIntersections(ray);
                if (intersectionPoints == null)
                      {
                    _imageWriter.writePixel(column, row, background);
                     }
                else
                    {
                  Intersectable.GeoPoint closestPoint = getClosestPoint(intersectionPoints);

                    _imageWriter.writePixel(column, row, calcColor(closestPoint).getColor());

                }
            }
        }
    }


    */
/**
     * claculates the color of each pixel
     * @param gp the shape and intersection
     * @return
     *//*

    private Color calcColor(Intersectable.GeoPoint gp) {
        int nShininess=0;double kd=0,ks=0;
        Color result = _scene.getAmbientLight().get_intensity();
        result = result.add(gp.getGeometry().get_emission());
        List<LightSource> lights = _scene.get_lights();

        Vector vecFromCameraToPointNormlized = gp.getPoint().subtract(_scene.getCamera().get_p0()).normalize();
        Vector normal = gp.getGeometry().getNormal(gp.getPoint());

        Material material = gp.getGeometry().get_material();
         if (material!=null)
         { nShininess = material.getnShininess();
          kd = material.getKd();
          ks = material.getKs();}
        if (_scene.get_lights() != null) {
            for (LightSource lightSource : lights) {

                Vector VecFromLightToPoint = lightSource.getL(gp.getPoint());
                double nl = alignZero(normal.dotProduct(VecFromLightToPoint));
                double nv = alignZero(normal.dotProduct(vecFromCameraToPointNormlized));

              //  if (nl *nv>0)
                {
                  //if (unshaded(lightSource,VecFromLightToPoint,normal,gp))
                    {
                    Color ip = lightSource.getIntensity(gp.getPoint());
                    result = result.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, VecFromLightToPoint, normal, nl, vecFromCameraToPointNormlized, nShininess, ip)
                    );
                }
                }
            }
        }

        return result;
    }

    */
/**
     *
     * @param light the light source emting
     * @param VecFromLight the vector from the light to specific point
     * @param normal normal of the shape
     * @param geoPoint the intersection point
     * @return true if there is no shapes blocking the light source
     *//*

  */
/*  private boolean unshaded(LightSource light, Vector VecFromLight, Vector normal, Intersectable.GeoPoint geoPoint) {
        Vector lightDirection = VecFromLight.scale(-1); // from point to light source
       Vector delta=normal.scale(normal.dotProduct(lightDirection)>0? DELTA:-DELTA);// fix the position of the intersection point

        Point3D pointGeo = geoPoint.getPoint().add(delta);//the new point on the shape
        Ray lightRay = new Ray(pointGeo, lightDirection);

        List<Intersectable.GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return true;//there is no shade
        }
        double lightDistance = light.getDistance(pointGeo);
        for (Intersectable.GeoPoint gp : intersections) {
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0) //if the shape is in between the light source or after the light source
            {
                return false;
            }
        }
        return true;
    }*//*


    private boolean unshaded(LightSource light, Vector l, Vector n, Intersectable.GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection);
        Point3D pointGeo = geopoint.getPoint();

        List<Intersectable.GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        double lightDistance = light.getDistance(pointGeo);
        for (Intersectable.GeoPoint gp : intersections) {
            double temp = gp.getPoint().distance(pointGeo) - lightDistance;
            if (alignZero(temp) <= 0)
                return false;
        }
        return true;
    }


    */
/**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component coef
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param v          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     * @author Dan Zilberstein
     * <p>
     * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
     * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
     * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
     * the surface.
     *//*

    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        double p = nShininess;

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        // [rs,gs,bs](-V.R)^p
        return ip.scale(ks * Math.pow(minusVR, p));
    }

    */
/**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     * @author Dan Zilberstein
     * <p>
     * The diffuse component is that dot product n•L that we discussed in class. It approximates light, originally
     * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
     * surface is paper. In general, you'll also want this to have a non-gray color value,
     * so this term would in general be a color defined as: [rd,gd,bd](n•L)
     *//*

    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) {
            nl = -nl;
        }

        return ip.scale(nl * kd);
    }

    private boolean sign(double val) {
        return (val > 0d);
    }
    */
/**
     * clac closest point in a list
     * @param intersectionPoints
     * @return
     *//*

    private Intersectable.GeoPoint getClosestPoint(List<Intersectable.GeoPoint> intersectionPoints)
    {
     Intersectable.GeoPoint result=null;
     double minDis=Double.MAX_VALUE;
     double Distance = 0;
     Point3D beginningPoint= _scene.getCamera().get_p0();


     for (Intersectable.GeoPoint geo: intersectionPoints)
     {
         Point3D point = geo.getPoint();
         Distance= beginningPoint.distance(geo.getPoint());
         if(Distance<minDis)
             minDis=Distance;
            result=geo;
     }
     return result;
    }


    */
/**
     * prints the grid on the image
     * @param interval the space between the lines
     * @param _lineColor lines color
     *//*

    public void printGrid(int interval, Color _lineColor)
    {
        int Ny=_imageWriter.getNy();
        int Nx=_imageWriter.getNx();

        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++)
            if (row % interval ==0||column % interval ==0)
                _imageWriter.writePixel(column,row,_lineColor.getColor());
        }
    }

    public void writeToImage()
    {
        _imageWriter.writeToImage();
    }

}
*/
