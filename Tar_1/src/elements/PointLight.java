package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * light source from a specific point
 * kC-Constant attenuation
 * kL- Linear attenuation
 * kQ-Quadratic attenuation
 */
public class PointLight extends Light implements LightSource {
  protected   Point3D _positionOfLight;
//public double radius;
    protected  double _kC;
    protected double _kL;
    protected double _kQ;

    public PointLight() {

    }

  //  public double getRadius() {
  //      return radius;
  //  }

 /*   public PointLight(Point3D _positionOfLight, double radius, double _kC, double _kL, double _kQ) {
        this._positionOfLight = _positionOfLight;
        this.radius = 0;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }*/

 /*   public PointLight(Color _intensity, Point3D _positionOfLight, double _kC, double _kL, double _kQ, double radius) {
        super(_intensity);
        this._positionOfLight = _positionOfLight;
        this.radius = radius;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }*/

    /**
     * constructor
     * @param colorIntensity
     * @param position of the light
     * @param kC-Constant attenuation
     * @param kL- Linear attenuation
     * @param kQ-Quadratic attenuation
     */
    public PointLight(Color colorIntensity, Point3D position, double kC, double kL, double kQ) {
        this._intensity = colorIntensity;
        this._positionOfLight = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     *     by default, the constant attenuation
     *     value is 1 and the other two values are 0
      */
    public PointLight(Color colorIntensity, Point3D position) {
        this(colorIntensity, position, 1d, 0d, 0d);
    }

   /* public List<Vector> getListRaysToLight(Point3D p) {
        // double size = _radius * _radius * Math.PI;
        List<Point3D> pointLights = new LinkedList<Point3D>();
        for (int x = 0; x < radius; x++)
            for (int y = 0; y < radius; y++)
                for (int z = 0; z < radius; z++) {
                    pointLights.add(new Point3D(_positionOfLight.get_x().get() + x, _positionOfLight.get_y().get() + y, _positionOfLight.get_z().get() + z));
                    pointLights.add(new Point3D(-_positionOfLight.get_x().get() + x, -_positionOfLight.get_y().get() + y, -_positionOfLight.get_z().get() + z));

                }
        pointLights=pointLights.stream().distinct().collect(Collectors.toList());
        if (p.equals(_positionOfLight)) {
            return null;
        }
        List<Vector> allSubstract = new LinkedList<Vector>();
        for (Point3D po : pointLights)
            allSubstract.add(p.subtract(po).normalize());
        return allSubstract;

    }*/

    /**
     *     overriding Light getIntensity()
     */
    @Override
    public Color get_intensity() {
        return super.get_intensity();
    }

    /**
     *     overriding LightSource getIntensity(Point3D)
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = new Vector(p, _positionOfLight).lengthSquared();
        ;
        double d = p.distance(_positionOfLight);

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }


    @Override
    public double getDistance(Point3D pointGeo) {
        return _positionOfLight.distance(pointGeo);
    }

    /**
     *
      * @param point
     * @return the vector from the light source to the point given
     */
    @Override
    public Vector getLightDirection(Point3D point) {
        if (point.equals(_positionOfLight)) {
            return null;
        }
        return point.subtract(_positionOfLight).normalize();
    }

    public Point3D get_positionOfLight() {
        return _positionOfLight;
    }

  /*  public  List<Ray> beemFromPoint(Point3D StartOfRay, PointLight light)
    //(int nX, int nY, Point3D StartOfRay,
    //  double screenDistance, double screenWidth, double screenHeight,
    //   double density, int amount,PointLight light)
    {
        List<Ray> rays = new LinkedList<>();

        //   double ratioY = screenHeight / nY;
        //   double ratioX = screenWidth / nX;
        //  double index=Math.sqrt(amount);
        //   double radius=light.getRadius();
        Point3D DirectionOfRay= light.get_positionOfLight();
        *//*    Vector VecTolight=DirectionOfRay.subtract(StartOfRay);
            Vector ortganl1=VecTolight.createorthogonalVec();
            Vector ortognal2=VecTolight.crossProduct(ortganl1);*//*

        //    double density2=Math.sqrt(index);
        for (int x = 0; x <= light.radius; x++) //create ray's
        {
            for (int y = 0; y <= light.radius; y++)
            {
                for (int z = 0; z <= light.radius; z++)
                {

                    rays.add(new Ray(StartOfRay, (new Vector(DirectionOfRay.get_x().get() + x, DirectionOfRay.get_y().get() + y, DirectionOfRay.get_z().get() + z))));

                    rays.add(new Ray(StartOfRay, (new Vector(-DirectionOfRay.get_x().get() + x, -DirectionOfRay.get_y().get() + y, -DirectionOfRay.get_z().get() + z))));

                }
            }
        }

        return rays;
    }*/
}