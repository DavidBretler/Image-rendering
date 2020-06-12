package renderer;
import elements.*;
import geometries.*;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Render {

    private static final  double DELTA=0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 40; //the max recrusiv calc
    private static final double MIN_CALC_COLOR_K = 0.000000001;

    private int Amount;
    private double densitiy;
    private Scene _scene;
    private ImageWriter _imageWriter;

    private static  final int MAX_RAYS = 6;
    public Render(Scene _scene) {
        this._scene = _scene;
    }

    public Render( ImageWriter imageWriter,Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
        this.densitiy =0d;
        this.Amount=50;
    }

    public Render(  ImageWriter _imageWriter,Scene _scene,int amount, double densitiy) {
        Amount = amount;
        this.densitiy = densitiy;
        this._scene = _scene;
        this._imageWriter = _imageWriter;
    }

    public Scene get_scene() {
        return _scene;
    }

    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     * culcs avrage of multiply ray from a pixel that crate ane middele color in the end of shape to crate smoother look
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
        if(densitiy ==0d)
        for (int row = 0; row < Ny; ++row) {
            for (int column = 0; column < Nx; ++column) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);

                if (intersectionPoints == null)
                    _imageWriter.writePixel(column, row, background);

                else
                    {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    java.awt.Color pixelColor = calcColor(closestPoint,ray ).getColor();
                    _imageWriter.writePixel(column, row, pixelColor);
                }
            }
        }

 else {    //supersampling
            for (int row = 0; row < Ny; row++)
            {
                for (int collumn = 0; collumn < Nx; collumn++) {
                        List<Ray> rays = camera.constructRayBeamThroughPixel(Nx, Ny, collumn, row, distance, width, height, densitiy,Amount);
                    Color averageColor = Color.BLACK;
                    //makes a list of the multiple rays that goes through different parts of the pixel
                  //  List<Ray> rays = getRaysThroughPixel(row, collumn);

                    //will store the colors in that pixel in a list later
                    //  List<Color> raysColors = new ArrayList<>();

                    Color Bckg = new Color(background);
                    double SumR=0;
                    double SumB=0;
                    double SumG=0;
                    for (Ray ray : rays) {
                        GeoPoint closestPoint = findClosestIntersection(ray);
                        if (closestPoint == null) {
                            //   averageColor = averageColor.add(Bckg);
                            SumR = Bckg.get_r();
                            SumB = Bckg.get_b();
                            SumG = Bckg.get_g();
                        } else {
                            //   averageColor = averageColor.add(calcColor(closestPoint, ray));
                            Color c = calcColor(closestPoint, ray);
                            SumR += c.get_r();
                            SumB += c.get_b();
                            SumG += c.get_g();
                        }

                        averageColor = new Color(SumR / rays.size(), SumG / rays.size(), SumB / rays.size());
                        //averageColor.scale(1d / rays.size());
                    }
                    if(averageColor.get_b()!=0)
                        averageColor=averageColor;
                    _imageWriter.writePixel(collumn, row, averageColor.getColor());
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
        GeoPoint resultPoint = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();

        for (GeoPoint geo : intersectionPoints) {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist) {
                mindist = distance;
                resultPoint = geo;
            }
        }
        return resultPoint;
    }

    /**
     * prints the grid on the image
     * @param interval the space between the lines
     * @param _lineColor lines color
     */
    public void printGrid(int interval, java.awt.Color _lineColor) {
        double rows = this._imageWriter.getNy();
        double collumns = _imageWriter.getNx();
        //Writing the lines.
        for (int row = 0; row < rows; ++row) {
            for (int collumn = 0; collumn < collumns; ++collumn) {
                if (collumn % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(collumn, row, _lineColor);
                }
            }
        }
    }

    public void writeToImage() {
        _imageWriter.writeToImage();

    }

    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
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
        Vector normal = geoPoint.getGeometry().getNormal(pointGeo).normalize();

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

        result = result.add(getLightSourcesColors(geoPoint, k, result, vecGeoCamera, normal, nShininess, kd, ks));

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
     * @param light the light source
     * @param l detraction of the light
     * @param n normal
     * @param geopoint  the inter point
     * @return transparency and shade effect on the intersection point
     */


    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1).normalize(); // from point to light source
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
     * @param point inter point
     * @param inRay
     * @return reflected ray
     */
    private Ray constructReflectedRay (Vector normal,Point3D point,Ray inRay) {//r=v-2*v*n*n
        Vector v = inRay.getDirection().normalize();
        double vn = v.dotProduct(normal);
        if (vn == 0)
        {
            return null;  }
        Vector r = v.add(normal.scale(-2 * vn)).normalize();

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
    private Color calcSpecular(double ks, Vector l, Vector n,  Vector v, int nShininess, Color ip) {
        double p = nShininess;
        double DP_n_l = alignZero(n.dotProduct(l));

        Vector R = l.add(n.scale(-2 * DP_n_l).normalize()); // nl must not be zero!
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

    //this function improves ray tracing by making multiple rays through different parts of the pixel


    private List<Ray> getRaysThroughPixel(double x, double y){

        //the ratio of the screen dimensions to the number of pixels
        double Rx = _imageWriter.getWidth() /_imageWriter.getNx();
        double Ry = _imageWriter.getHeight() / _imageWriter.getNy();

        //store the rays in a list
        List<Ray> raysThroughPixel = new ArrayList<>();

        //i will go from -1/2 * the max to 1/2 max
        for (int i = (-1 * MAX_RAYS) / 2; i <= MAX_RAYS / 2; i++) {
            for (int j = (-1 * MAX_RAYS) / 2; j <= MAX_RAYS / 2; j++) {

                //calculating the coordinate of the offset rays
                double iOffSet = (i * Rx) / MAX_RAYS;
                double jOffSet = (j * Ry) / MAX_RAYS;
                //making a ray to that position and placing it in the list
                Ray ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), (int)(x+iOffSet), (int)(y + jOffSet), _scene.getDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
                raysThroughPixel.add(ray);
            }
        }

        return raysThroughPixel;
    }

    /**
     *
     * @param geoPoint intersec point on object
     * @param k
     * @param color
     * @param v vec
     * @param n norml
     * @param nShininess Shininess factor of matireal
     * @param kd  diffusive  factor of matireal
     * @param ks   specular factor of matireal
     * culcs a shopt shadow from light to object by sending multiply ray from point to light and culcs avg of ktr of all rays
     * @return final color in point
     */
    private Color getLightSourcesColors(GeoPoint geoPoint, double k, Color color,
                                       Vector v, Vector n, int nShininess,
                                       double kd, double ks)
    {
        Point3D pointGeo = geoPoint.getPoint();
        if (_scene.get_lights() != null)
        {

            for (LightSource lightSource : _scene.get_lights()) {

                if (((PointLight)lightSource).getRadius() == 0)
                {
                    Vector l = lightSource.getLightDirection(pointGeo).normalize();

                    double nl = n.dotProduct(l);
                    double nv = n.dotProduct(v);
                    double ktr;
                    if (nl * nv > 0) {

                        ktr = transparency(lightSource, l, n, geoPoint);
                        if (ktr * k > MIN_CALC_COLOR_K) {
                            Color lightIntensity = lightSource.getIntensity(pointGeo).scale(ktr);
                            color = color.add(
                                    calcDiffusive(kd, nl, lightIntensity),
                                    calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                        }
                    }
                    return color;
                }
                else
                    {
                    double ktr=0.0;
                    double sizeVecList=0.0;

                    // double  nl2=0.0;
                    double nv=0.0;
                  for (LightSource lightSource2 : _scene.get_lights())
                        for (Ray ray : ((PointLight)lightSource).beemFromPoint(pointGeo,(PointLight)lightSource))
                        {
                            sizeVecList += 1;
                            double  nl = n.dotProduct(ray.getDirection());

                            nv = n.dotProduct(v);

                            if (nl * nv > 0) {

                                ktr += transparency(lightSource, ray.getDirection().normalize(), n, geoPoint);
                            }
                        }

                    ktr = ktr / (sizeVecList/2);
                    Vector l = lightSource.getLightDirection(pointGeo).normalize();
                    double nl = n.dotProduct(l);
                    // double nl = n.dotProduct(l);
                    if (ktr * k > MIN_CALC_COLOR_K) {
                        Color lightIntensity = lightSource.getIntensity(pointGeo).scale(ktr);
                        color = color.add(
                                calcDiffusive(kd, nl, lightIntensity),
                                calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                    }
                }
            }
            return color;

        }
        return Color.BLACK;
    }
}
