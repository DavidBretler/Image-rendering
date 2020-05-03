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
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        Ray ray;
        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                ray = camera.constructRayThroughPixel(Nx, Ny, row, column, distance, width, height);
            //    List<Intersectable.GeoPoint> intersectionPoints =new Intersectable.GeoPoint(_scene.getGeometries().findIntersections(ray));
                 List<Point3D> intersectionPoints = _scene.getGeometries().findIntersections(ray);
                // TODO: 03/05/2020
                if (intersectionPoints == null)
                      {
                    _imageWriter.writePixel(column, row, background);
                     }
                else
                    {
                   // Intersectable.GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                  Point3D pt = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(column-1, row-1, calcColor(pt) );
                }
            }
        }
    }



    private Color calcColor(Point3D point) {
        return _scene.getAmbientLight().getIntensity();
    }
    private Intersectable.GeoPoint getClosestPoint(List<Intersectable.GeoPoint> intersectionPoints) {
        Intersectable.GeoPoint result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();

        for (Intersectable.GeoPoint geo: intersectionPoints ) {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist){
                mindist= distance;
                result =geo;
            }
        }
        return  result;
    }
    public void printGrid(int interval, java.awt.Color color){}

    public void writeToImage() {
    }
}
