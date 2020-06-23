package renderer;
import elements.*;
import geometries.*;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;
import java.math.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Render {
    private static final  double DELTA=0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 40; //the max recrusiv calc
    private static final double MIN_CALC_COLOR_K = 0.000000001;
    private static final double adptivRecrsivLevel=4;
    private int Amount;
    private double densitiy;
    private Scene _scene;
    private ImageWriter _imageWriter;
        // ...........
        private int _threads = 5;
        private final int SPARE_THREADS =2;
        private boolean _print = false;

    public Render() {

    }

    /**
     *weightRay help the AdaptiveSuperSampaling by weight every ray acording to the recursive depth
     */
    public class weightRay extends Ray
    {
        public Ray ray;
        double weight=1 ;

        public weightRay(Ray ray, double weight)
        {
            super(ray);
           this.ray=ray;
            this.weight = weight;
        }
        public weightRay(Ray ray)
        {
            super(ray);
            this.ray=ray;
            this.weight = 1;
        }

    }
    /**
         * Pixel is an internal helper class whose objects are associated with a Render object that
         * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
         * its progress.<br/>
         * There is a main follow up object and several secondary objects - one in each thread.
         * @author Dan
         *
         */
        private class Pixel {
            private long _maxRows = 0;
            private long _maxCols = 0;
            private long _pixels = 0;
            public volatile int row = 0;
            public volatile int col = -1;
            private long _counter = 0;
            private int _percents = 0;
            private long _nextCounter = 0;

            /**
             * The constructor for initializing the main follow up Pixel object
             * @param maxRows the amount of pixel rows
             * @param maxCols the amount of pixel columns
             */
            public Pixel(int maxRows, int maxCols) {
                _maxRows = maxRows;
                _maxCols = maxCols;
                _pixels = maxRows * maxCols;
                _nextCounter = _pixels / 100;
                if (Render.this._print) System.out.printf("\r %02d%%", _percents);
            }

            /**
             *  Default constructor for secondary Pixel objects
             */
            public Pixel() {}

            /**
             * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
             * critical section for all the threads, and main Pixel object data is the shared data of this critical
             * section.<br/>
             * The function provides next pixel number each call.
             * @param target target secondary Pixel object to copy the row/column of the next pixel
             * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
             * finished, any other value - the progress percentage (only when it changes)
             */
            private synchronized int nextP(Pixel target) {
                ++col;
                ++_counter;
                if (col < _maxCols) {
                    target.row = this.row;
                    target.col = this.col;
                    if (_counter == _nextCounter) {
                        ++_percents;
                        _nextCounter = _pixels * (_percents + 1) / 100;
                        return _percents;
                    }
                    return 0;
                }
                ++row;
                if (row < _maxRows) {
                    col = 0;
                    if (_counter == _nextCounter) {
                        ++_percents;
                        _nextCounter = _pixels * (_percents + 1) / 100;
                        return _percents;
                    }
                    return 0;
                }
                return -1;
            }

            /**
             * Public function for getting next pixel number into secondary Pixel object.
             * The function prints also progress percentage in the console window.
             * @param target target secondary Pixel object to copy the row/column of the next pixel
             * @return true if the work still in progress, -1 if it's done
             */
            public boolean nextPixel(Pixel target) {
                int percents = nextP(target);
                if (percents > 0)
                    if (Render.this._print) System.out.printf("\r %02d%%", percents);
                if (percents >= 0)
                    return true;
                if (Render.this._print) System.out.printf("\r %02d%%", 100);
                return false;
            }
        }



    /**
         * This function renders image's pixel color map from the scene included with
         * the Renderer object uses multi-threads method to improve runtime
         */
        public void renderImage() {
            final int nX = _imageWriter.getNx();
            final int nY =_imageWriter.getNy();
            final double dist = _scene.getDistance();
            final double width = _imageWriter.getWidth();
            final double height = _imageWriter.getHeight();
            final Camera camera = _scene.getCamera();

            final Pixel thePixel = new Pixel(nY, nX);

            // Generate threads
            Thread[] threads = new Thread[_threads];
            for (int i = _threads - 1; i >= 0; --i) {
                threads[i] = new Thread(() ->
                {
                    Pixel pixel = new Pixel();
                    while (thePixel.nextPixel(pixel)) {
                        LinkedList<Ray> rays=new LinkedList<Ray>();

                        //if amount of ray's is 1 so there is no super smaplling
                        if (this.Amount<=1)
                        {
                            rays.add( camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row,dist, width, height));
                        _imageWriter.writePixel(pixel.col, pixel.row, calcColor(rays).getColor());
                        }
                        else
                        {
                            LinkedList<weightRay> weightRays;

                          //  rays=(camera.constructRayBeamThroughPixel(nX, nY, pixel.col, pixel.row, dist, (double)width, (double)height, this.densitiy, this.Amount));
                            weightRays=(AdaptiveSuperSampaling(nX, nY, pixel.col, pixel.row, dist, (double)width, (double)height));

                               if(weightRays.isEmpty())//all the pixel is in the same color so we can send one ray
                               {
                                   rays.add( camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row,dist, width, height));
                               _imageWriter.writePixel(pixel.col, pixel.row, calcColor(rays).getColor());
                               }
                               else
                                   _imageWriter.writePixel(pixel.col, pixel.row, adptivCalcColor(weightRays).getColor());

                    }

                    }
                });
            }

            // Start threads
            for (Thread thread : threads) thread.start();

            // Wait for all threads to finish
            for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
            if (_print) System.out.printf("\r100%%\n");
        }

        /**
         * Set multithreading <br>
         * - if the parameter is 0 - number of coress less 2 is taken
         *
         * @param threads number of threads
         * @return the Render object itself
         */
        public Render setMultithreading(int threads) {
            if (threads < 0)
                throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
            if (threads != 0)
                _threads = threads;
            else {
                int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
                if (cores <= 2)
                    _threads = 1;
                else
                    _threads = cores;
            }
            return this;
        }

        /**
         * Set debug printing on
         *
         * @return the Render object itself
         */
        public Render setDebugPrint() {
            _print = true;
            return this;
        }



    private static  final int MAX_RAYS = 6;
    public Render(Scene _scene) {
        this._scene = _scene;
    }

    public Render( ImageWriter imageWriter,Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
        this.densitiy =1d;
        this.Amount=1;
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
    public GeoPoint findClosestIntersection(Ray ray) {

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
     *  calcs avrage of multiply ray from a pixel by calc the ray according to its weight
     *  to create avg color in the end of shape to create smoother look
     * @return avg color
     */
    private Color adptivCalcColor(LinkedList<weightRay> rays) {
        GeoPoint centerPiX = findClosestIntersection(rays.get(0));
        Color Bckg = new Color(_scene.getBackground());

        //       if (ClosesPoint==null)
        //           return Bckg;

        if (rays.size() == 1)
        {
            if (centerPiX==null)
                return Bckg;
            else
            {
                Color resultColor = _scene.getAmbientLight().get_intensity();
                return  resultColor.add(calcColor(centerPiX, rays.get(0), MAX_CALC_COLOR_LEVEL, 1.0));}
        }
        else
        {
            Color averageColor = Color.BLACK;

            double SumR = 0;
            double SumB = 0;
            double SumG = 0;
            for (weightRay ray : rays) {
                GeoPoint closestPoint = findClosestIntersection(ray);
                if (closestPoint == null) {
                    SumR += Bckg.get_r()*ray.weight;
                    SumB += Bckg.get_b()*ray.weight;
                    SumG += Bckg.get_g()*ray.weight;
                } else {
                    LinkedList<weightRay> rays1 = new LinkedList<>();
                    rays1.add(ray);
                    Color c = adptivCalcColor(rays1);
                    SumR += c.get_r()*ray.weight;
                    SumB += c.get_b()*ray.weight;
                    SumG += c.get_g()*ray.weight;
                }

            }
            averageColor = new Color(SumR / rays.size(), SumG / rays.size(), SumB / rays.size());
            if( centerPiX!=null)
                return   averageColor.add(calcColor(centerPiX, rays.get(4), MAX_CALC_COLOR_LEVEL, 1.0));
            return averageColor;
        }
    }

    /**
     * the entail call to calc color of the pixel
     *  calcs avrage of multiply ray from a pixel to create avg color in the end of shape to create smoother look
     * @return avg color
     */
    private Color calcColor(LinkedList<Ray> rays) {
        GeoPoint centerPiX = findClosestIntersection(rays.get(0));
        Color Bckg = new Color(_scene.getBackground());

 //       if (ClosesPoint==null)
 //           return Bckg;

        if (rays.size() == 1)
        {
            if (centerPiX==null)
                return Bckg;
            else
            {
                Color resultColor = _scene.getAmbientLight().get_intensity();
                 return  resultColor.add(calcColor(centerPiX, rays.get(0), MAX_CALC_COLOR_LEVEL, 1.0));}
        }
        else
            {
            Color averageColor = Color.BLACK;

            double SumR = 0;
            double SumB = 0;
            double SumG = 0;
            for (Ray ray : rays) {
                GeoPoint closestPoint = findClosestIntersection(ray);
                if (closestPoint == null) {
                    SumR += Bckg.get_r();
                    SumB += Bckg.get_b();
                    SumG += Bckg.get_g();
                } else {
                    LinkedList<Ray> rays1 = new LinkedList<>();
                    rays1.add(ray);
                    Color c = calcColor(rays1);
                    SumR += c.get_r();
                    SumB += c.get_b();
                    SumG += c.get_g();
                }

            }
              averageColor = new Color(SumR / rays.size(), SumG / rays.size(), SumB / rays.size());
               if( centerPiX!=null)
               return   averageColor.add(calcColor(centerPiX, rays.get(0), MAX_CALC_COLOR_LEVEL, 1.0));
                return averageColor;
            }
         /*     *//*      Color result = ClosestPoint.getGeometry().get_emission();//original color of geometry
                Point3D pointGeo = ClosestPoint.getPoint();

                Vector vecGeoCamera = pointGeo.subtract(_scene.getCamera().get_p0()).normalize();//vector from geometry to camera
                Vector normal = ClosestPoint.getGeometry().getNormal(pointGeo).normalize();

                double DP_Normal_vecGeoCamera = alignZero(normal.dotProduct(vecGeoCamera));
                if (DP_Normal_vecGeoCamera == 0) {
                    //ray parallel to geometry surface ??
                    //and orthogonal to normal
                    return result;
                }
                *//*
            Material material = ClosestPoint.getGeometry().get_material();
            int nShininess = material.getnShininess();
            double kd = material.getKd();
            double ks = material.getKs();
            double kr = ClosestPoint.getGeometry().get_material().getKr();
            double kt = ClosestPoint.getGeometry().get_material().getKt();

            averageColor = new Color(SumR / rays.size(), SumG / rays.size(), SumB / rays.size());

            for (LightSource lightSource : _scene.get_lights()) {
                Color lightIntensity = lightSource.getIntensity(ClosestPoint.getPoint()).scale(kr);
                //  averageColor = averageColor.add(getLightSourcesColors(ClosestPoint, kr, result, vecGeoCamera, normal, nShininess, kd, ks));
                Vector l = lightSource.getLightDirection(ClosestPoint.getPoint()).normalize();
                Vector n = ClosestPoint.getGeometry().getNormal(ClosestPoint.getPoint()).normalize();
                double nl = n.dotProduct(l);
                Vector v = ClosestPoint.getPoint().subtract(_scene.getCamera().get_p0()).normalize();//vector from geometry to camera

                averageColor = averageColor.add(
                        calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }


            return averageColor;*/
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
       if(level<39)
           level=level;
        if (level == 1 || k < MIN_CALC_COLOR_K) //stop condition
        {
            return Color.BLACK; //0,0,0
        }

        //if  (geoPoint==null)
        //   return _scene.getBackground();
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
            if (refractedPoint != null)
            {
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
    /**
     *
     * @param nX             number of pixel in x axis
     * @param nY             number of pixel in y axis
     * @param j              index in x axis
     * @param i              index in x axis
     * @param screenDistance distance form camera to view plane
     * @param screenWidth    screen width
     * @param screenHeight   screen height
     *  check if all the pixel is in the same color and if so we will not start the recursive check
     * @return list of weight rays , every ray acording to its recursive depth
     */
    public LinkedList<weightRay> AdaptiveSuperSampaling(int nX, int nY, int j, int i,
                                                  double screenDistance, double screenWidth, double screenHeight )
    {
        int level=0;
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0"); //view plane is on the camera
        }

        LinkedList<weightRay> rays = new LinkedList<>();

        Point3D pixCenter = _scene.getCamera().get_p0().add(_scene.getCamera().get_vTo().scale(screenDistance)); //


        double ratioY = screenHeight / nY;
        double ratioX = screenWidth / nX;

        double yi = ((i - nY / 2d) * ratioY);//calc the move of the pixel to our pixel
        double xj = ((j - nX / 2d) * ratioX);

        Point3D Pij = pixCenter;

        //move the  center point to wanted pixel
        if (!isZero(xj))
        {
            Pij = Pij.add(_scene.getCamera().get_vRight().scale(xj));
        }
        if (!isZero(yi))
        {
            Pij = Pij.subtract(_scene.getCamera().get_vUp().scale(yi).get_head()).get_head();
        }

        //4 rays thwro edeges of pixel x=0
        Point3D point = Pij;
        rays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0()))));

        point = point.add(_scene.getCamera().get_vUp().scale((-ratioY)));

        rays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0()))));
        point = point.add(_scene.getCamera().get_vRight().scale((ratioX)));
        rays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0()))));
        point = point.add(_scene.getCamera().get_vUp().scale((ratioY)));
        rays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0()))));

        LinkedList<Color> colors =getColorsOfRays(rays);

        //if (colors.get(1).get_b()!=0)
        //    _print=_print;
        boolean flage = false;
        for (int k = 0; k < colors.size()-1; k++)
            if (!colors.get(k).equals(colors.get(k + 1)))
                flage = true;
        if (flage)
            recursivAdaptiveSuperSampaling(100, 100,
                    100, 100d, rays,Pij,level);
         else
             rays.clear();
            return  rays;
       //  return null;
    }

    /**
     *
     * @param nX             number of pixel in x axis
     * @param nY             number of pixel in y axis
     * @param screenWidth    screen width
     * @param screenHeight   screen height
     * @param level the depth of the recursive
     * @param thisPix the upper left point of the wanted part of pixel
     * @return list of weight rays of the specipic recursive call
     */
    public LinkedList<weightRay> recursivAdaptiveSuperSampaling(int nX, int nY,
     double screenWidth, double screenHeight, LinkedList<weightRay> rays,Point3D thisPix,int level)
    {
            if (level>=adptivRecrsivLevel)
                return rays;

        double ratioY = screenHeight / nY;
        double ratioX = screenWidth / nX;

        Point3D Pij = thisPix;

        LinkedList<weightRay> nineRays=  nineRaysThroPixel(Pij,ratioX,ratioY,level);

        LinkedList<Color> colors =getColorsOfRays(nineRays);

        boolean flag  ;
        for (int k = 0; k < 4; k++) {
            flag =false ;
            Pij = thisPix;
            if (k == 2)
                k = 3;//fix index
            if (!colors.get(k).equals(colors.get(k + 1)) || !colors.get(k + 1).equals(colors.get(k + 3)) || !colors.get(k + 3).equals(colors.get(k + 4)))
                 flag = true;
            if (flag)
            {
                if (k != 0 && k != 3)
                    Pij = Pij.add(_scene.getCamera().get_vRight().scale((ratioX / 2)));
                if (k != 0 && k != 1)
                    Pij = Pij.add(_scene.getCamera().get_vUp().scale((-ratioY / 2)));
                recursivAdaptiveSuperSampaling(nX, nY,
                        screenWidth / 4, screenHeight / 4, rays, Pij, level + 1);
            }
        }
        rays.addAll(nineRays);
        return  rays;
    }

    /**
     *
     * @param temprays
     * @return list of colors according to the list of rays
     */
    private LinkedList<Color> getColorsOfRays(LinkedList<weightRay> temprays )
    {
        Color Bckg = new Color(_scene.getBackground());
        GeoPoint edgeIntersec = new GeoPoint();
        Color result;
        LinkedList<Color> colors = new LinkedList<Color>();

        for (weightRay ray : temprays)
        {
            edgeIntersec = findClosestIntersection(ray.ray);
            if (edgeIntersec==null)
                result= Bckg;
            else
            result = edgeIntersec.getGeometry().get_emission();
            colors.add(result);
        }
      return colors;
    }

    /**
     *
     * @param point the upper left point of the wanted part of pixel
     * @param ratioX x exil of pixel
     * @param ratioY y exil of pixel
     * @param level depth of recursive
     * @return add 9 rays throw the wanted part in pixel to the list
     */
    private LinkedList<weightRay> nineRaysThroPixel(Point3D point,double ratioX, double ratioY,int level)

    {
        LinkedList<weightRay> temprays = new LinkedList<>();
        double whiet =Math.pow((double)4/9,level+2);
        //first qurter
        temprays.add( new weightRay( new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0()))  ,whiet)  );

        point = point.add(_scene.getCamera().get_vRight().scale((ratioX/2)));
        temprays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0())),whiet));

        point = point.add(_scene.getCamera().get_vRight().scale((ratioX/2)));
        temprays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0())),whiet));
        //2 more to second qurter

        point = point.add(_scene.getCamera().get_vUp().scale((-ratioY/2)));
        point = point.add(_scene.getCamera().get_vRight().scale((-ratioX)));
        temprays.add(new weightRay((new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0()))),whiet));

        point = point.add(_scene.getCamera().get_vRight().scale((ratioX/2)));
        temprays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0())),whiet));

        point = point.add(_scene.getCamera().get_vRight().scale((ratioX/2)));
        temprays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0())),whiet));
        //3 row
        point = point.add(_scene.getCamera().get_vUp().scale((-ratioY/2)));
        point = point.add(_scene.getCamera().get_vRight().scale((-ratioX)));
        temprays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0())),whiet));

        point = point.add(_scene.getCamera().get_vRight().scale((ratioX/2)));
        temprays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0())),whiet));

        point = point.add(_scene.getCamera().get_vRight().scale((ratioX/2)));
        temprays.add(new weightRay(new Ray(_scene.getCamera().get_p0(), point.subtract(_scene.getCamera().get_p0())),whiet));

        return temprays;
    }

}
   //     for (int r = 0; r < 9; r++)
   //     {

  //      }
  /*   for (int r = 0; r < 3; r++)
        {
            if (!isZero(r))
            point = Pij.add( _vUp.scale((r * nY / 2)));
           for (int c = 0; c < 3; c++)
           {
               if (!isZero(c))
                 point.add(_vRight.scale((c * nX / 2)));
            rays.add(new Ray(_p0, point.subtract(_p0)));//add new ray to list of ray's from point

           }

        }



}*/
