package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.awt.*;
import java.util.List;

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

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        double width = (double) _imageWriter.getWidth();
        double height = (double) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        Ray ray;
        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                ray = camera.constructRayThroughPixel(Nx, Ny, row, column, distance, width, height);

                 List<Point3D> intersectionPoints = _scene.getGeometries().findIntersections(ray);
                // TODO: 03/05/2020
                if (intersectionPoints == null)
                      {
                    _imageWriter.writePixel(column, row, background);
                     }
                else
                    {
                  Point3D closestPoint = getClosestPoint(intersectionPoints);
                  java.awt.Color pixelColor=calcColor(closestPoint);
                    _imageWriter.writePixel(column-1, row-1, pixelColor);
                }
            }
        }
    }



    private Color calcColor(Point3D point) {
        return _scene.getAmbientLight().getIntensity();
    }

    /**
     * clac closest point in a list
     * @param intersectionPoints
     * @return
     */
    private Point3D  getClosestPoint(List<Point3D> intersectionPoints)
    {
     Point3D beginningPoint= _scene.getCamera().get_p0();
     Point3D closestPoint=null;
     Double minDis=Double.MAX_VALUE;
     Double distance=0d;
     for (Point3D point: intersectionPoints)
     {
         distance=beginningPoint.distance(point);
         if(distance<minDis)
             minDis=distance;
            closestPoint=point;
     }
     return closestPoint;
    }


    public void printGrid(int interval, java.awt.Color _lineColor)
    {
        int Ny=_imageWriter.getNy();
        int Nx=_imageWriter.getNx();

        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++)
            if (row % interval ==0||column % interval ==0)
                _imageWriter.writePixel(column,row,_lineColor);
        }
    }

    public void writeToImage()
    {
        _imageWriter.writeToImage();
    }

}
