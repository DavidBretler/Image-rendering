package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

//import java.awt.*;
import java.util.List;

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
                    _imageWriter.writePixel(column-1, row-1, calcColor(closestPoint).getColor());
                }
            }
        }
    }


    /**
     * claculates the color of each pixel
     * @param geo the shape and intersection
     * @return
     */
    private Color calcColor(Intersectable.GeoPoint geo) {

        Color color = _scene.getAmbientLight().get_intensity();
        color = color.add(geo.geometry.get_emission());
        return color;
    }

    /**
     * clac closest point in a list
     * @param intersectionPoints
     * @return
     */
    private Intersectable.GeoPoint getClosestPoint(List<Intersectable.GeoPoint> intersectionPoints)
    {
     Intersectable.GeoPoint result=null;
     Double minDis=Double.MAX_VALUE;

        Point3D beginningPoint= _scene.getCamera().get_p0();


     for (Intersectable.GeoPoint geo: intersectionPoints)
     {
         Point3D point = geo.point;
         Double distance= beginningPoint.distance(point);
         if(distance<minDis)
             minDis=distance;
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
