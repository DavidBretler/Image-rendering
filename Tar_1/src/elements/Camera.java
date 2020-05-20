package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;


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


//        this._vRight = _vUp.normalized().scale(-1);
//        this._vUp = this._vRight.crossProduct(this._vTo).normalize().scale(-1);

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

}
