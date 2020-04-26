package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera
{
    Point3D _position ;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;


    public Point3D get_position() {
        return _position;
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
     * @param _position Position of the camera
     * @param _vTo vector towards the view plane
     * @param _vUp vector  going up from camera
     */
    public Camera(Point3D _position, Vector _vTo, Vector _vUp) {

        this._position = _position;
        this._vTo = _vTo.normalize();
        this._vUp = _vUp.normalize();
if(!isZero(get_vTo().dotProduct(_vUp)))
     throw new IllegalArgumentException("The V_to and V_up vectors are not orthogonal.");
        else
            _vRight = _vTo.crossProduct(_vUp).normalized();
    }

    /**
     *
     * @param nX number of pixel in x line
     * @param nY number of pixel in y line
     * @param j the column index of the pixel that the ray intercept
     * @param i the  row index of the pixel that the ray intercept
     * @param screenDistance the distance from the camera to the view plan
     * @param screenWidth the width of the view plane
     * @param screenHeight the Height of the view plane
      _Rx width of the pixel
      -ry Height of the pixel
     * @return ray that intercept the view plane in the correct point
     */
    public Ray constructRayThroughPixel (int nX, int nY,
                                         int j, int i, double screenDistance,
                                         double screenWidth, double screenHeight)
    {
        if (isZero(screenDistance))
            throw new IllegalArgumentException("distance cannot be 0");

            Vector pIJ = new Vector(_position.add(_vTo.scale(screenDistance))) ;
       double _Rx=screenWidth/nX;
        double _Ry=screenHeight/nY;

        double _yI=((i-nY/2d)*_Ry)+(_Ry/2d);
        double _xJ=((j-nX/2d)*_Rx)+(_Rx/2d);

        if (_xJ != 0) pIJ = pIJ.add(_vRight.scale(_xJ));
        if (_yI != 0) pIJ = pIJ.add(_vUp.scale(-_yI));
        return new Ray( _position,pIJ.normalize());

    }
}
