package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static primitives.Util.isZero;
import  java.lang.Math;
public class Camera {
    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;
    private static final Random rnd = new Random();

    public Point3D get_p0() {
        return _p0;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public Vector get_vRight() {
        return _vRight;
    }

    /**
     * constructor normalises the vectors and calc the v_right Vector
     *
     * @param _p0  Position of the camera
     * @param _vTo vector towards the view plane
     * @param _vUp vector  going up from camera
     */
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {
        if (!isZero(_vTo.dotProduct(_vUp)))
            throw new IllegalArgumentException("The V_to and V_up vectors are not orthogonal.");

        this._p0 = new Point3D(_p0);
        this._vTo = _vTo.normalized();

        this._vUp = _vUp.normalized();
        this._vRight =   this._vUp.crossProduct(this._vTo).scale(-1);




    }

    /**
     * @param nX             number of pixel in x line
     * @param nY             number of pixel in y line
     * @param j              the column index of the pixel that the ray intercept
     * @param i              the  row index of the pixel that the ray intercept
     * @param screenDistance the distance from the camera to the view plan
     * @param screenWidth    the width of the view plane
     * @param screenHeight   the Height of the view plane
     *                       _Rx width of the pixel
     *                       -ry Height of the pixel
     * @return ray that intercept the view plane in the correct point
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        if (isZero(screenDistance))
            throw new IllegalArgumentException("distance from camera cannot be 0");


        Point3D Pc = _p0.add(_vTo.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        if (!isZero(xj))
            Pij = Pij.add(_vRight.scale(xj));

        if (!isZero(yi))
            Pij = Pij.add((_vUp.scale(-yi)));

        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0, Vij);

    }

    /**
     *
     * @param nX number of pixel in x axis
     * @param nY number of pixel in y axis
     * @param j index in x axis
     * @param i index in x axis
     * @param screenDistance distance form camera to view plane
     * @param screenWidth screen width
     * @param screenHeight screen height
     * @param density the density factor  of the ray's in the beam
     * @param amount number of ray's in beam
     * @return list of ray's in the pixel
     */
    public List<Ray> constructRayBeamThroughPixel(int nX, int nY, int j, int i,
                                                  double screenDistance, double screenWidth, double screenHeight,
                                                  double density, int amount) {
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0"); //view plane is on the camera
        }

        List<Ray> rays = new LinkedList<>();

        Point3D pixCenter = _p0.add(_vTo.scale(screenDistance)); //

        double ratioY = screenHeight / nY;
        double ratioX = screenWidth / nX;

        double yi = ((i - nY / 2d) * ratioY + ratioY / 2d);//calc the move of the pixel to our pixel
        double xj = ((j - nX / 2d) * ratioX + ratioX / 2d);

        Point3D Pij = pixCenter;

        //move the pixel to pixel
        if (!isZero(xj)) {
            Pij = Pij.add(_vRight.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.subtract(_vUp.scale(yi).get_head()).get_head();
        }

        //antialiasing density >= 1
  //      double radius = (ratioX + ratioY) / 2d * density; //calc  the size of the beam
     //   if(radius > ratioX||radius > ratioY)
       //     radius=ratioX;

        double index=Math.sqrt(amount);
     //   double density2=Math.sqrt(index);
        for (int row = 0; row < index; row++) //create ray's
        {
            for (int column = 0; column < index; column++)
            {  Point3D point = new Point3D(Pij);


            if (!isZero(row)) {
                point = point.add(_vRight.scale((0.1+rnd.nextDouble())*density*(row*ratioX)/index));
            }
            if (!isZero(column)) {
                point = point.add(_vUp.scale(density*((0.1+rnd.nextDouble())*column*ratioY)/index));
            }
            rays.add(new Ray(_p0, point.subtract(_p0)));//add new ray to list of ray's from point
        }
        }
        return rays;
    }


    public  List<Ray> beemFromPoint(int nX, int nY, Point3D _p,
                                    double screenDistance, double screenWidth, double screenHeight,
                                    double density, int amount)
        {
            List<Ray> rays = new LinkedList<>();

            double ratioY = screenHeight / nY;
            double ratioX = screenWidth / nX;
            double index=Math.sqrt(amount);
        //    double density2=Math.sqrt(index);
        for (int row = 0; row < index; row++) //create ray's
        {
            for (int column = 0; column < index; column++)
            {  Point3D point = new Point3D(_p);

                if (!isZero(row)) {
                    point = point.add(_vRight.scale(density*(row*ratioX)/index));
                }
                if (!isZero(column)) {
                    point = point.add(_vUp.scale(density*(column*ratioY)/index));
                }
                rays.add(new Ray(_p0, point.subtract(_p0)));//add new ray to list of ray's from point
            }}
        return rays;
    }
}
