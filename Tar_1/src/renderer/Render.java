package renderer;


import elements.*;
import geometries.*;
import primitives.*;
import scene.Scene;

//import java.awt.*;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * renders a image form the scene and saves as jpeg file
 */
public class Render
{

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

        /**
        width and height are the number of pixels in the rows
        //and columns of the view plane
        */
        double width = (double) _imageWriter.getWidth();
        double height = (double) _imageWriter.getHeight();

        /**
         * Nx and Ny are the width and height of the image.
         */
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        Ray ray;
        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                ray = camera.constructRayThroughPixel(Nx, Ny, row, column, distance, width, height);

                 List<Intersectable.GeoPoint> intersectionPoints = _scene.getGeometries().findIntersections(ray);
                if (intersectionPoints == null)
                      {
                    _imageWriter.writePixel(column, row, background);
                     }
                else
                    {
                  Intersectable.GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                        // TODO: 18/05/2020 fix bag ! //
                    _imageWriter.writePixel(column-1, row-1, calcColor(closestPoint).getColor());

                }
            }
        }
    }


    /**
     * claculates the color of each pixel
     * @param gp the shape and intersection
     * @return
     */
    private Color calcColor(Intersectable.GeoPoint gp) {
        int nShininess=0;double kd=0,ks=0;
        Color result = _scene.getAmbientLight().get_intensity();
        result = result.add(gp.getGeometry().get_emission());
        List<LightSource> lights = _scene.get_lights();

        Vector v = gp.getPoint().subtract(_scene.getCamera().get_p0()).normalize();
        Vector n = gp.getGeometry().getNormal(gp.getPoint());

        Material material = gp.getGeometry().get_material();
         if (material!=null)
         { nShininess = material.getnShininess();
          kd = material.getKd();
          ks = material.getKs();}
        if (_scene.get_lights() != null) {
            for (LightSource lightSource : lights) {

                Vector l = lightSource.getL(gp.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));

                if (sign(nl) == sign(nv)) {
                    Color ip = lightSource.getIntensity(gp.getPoint());
                    result = result.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n, nl, v, nShininess, ip)
                    );
                }
            }
        }

        return result;
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
        // [rs,gs,bs](-V.R)^p
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
     * surface is paper. In general, you'll also want this to have a non-gray color value,
     * so this term would in general be a color defined as: [rd,gd,bd](n•L)
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) {
            nl = -nl;
        }

        return ip.scale(nl * kd);
    }

    private boolean sign(double val) {
        return (val > 0d);
    }
    /**
     * clac closest point in a list
     * @param intersectionPoints
     * @return
     */
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


    /**
     * prints the grid on the image
     * @param interval the space between the lines
     * @param _lineColor lines color
     */
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
