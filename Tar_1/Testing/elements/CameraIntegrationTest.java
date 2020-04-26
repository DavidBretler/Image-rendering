package elements;


import java.util.List;
import java.util.function.Supplier;


import java.util.List;
import java.util.function.Supplier;

import geometries.Sphere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class CameraIntegrationTest
  {
      Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
      Sphere sph= new Sphere(1,new Point3D(0,0,3));
Ray _pix00=new Ray (camera.constructRayThroughPixel(3,3,0,0,1,6,6));

       Assertions.

  }
