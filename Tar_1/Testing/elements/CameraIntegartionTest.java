package elements;

import geometries.Sphere;

import org.junit.jupiter.api.Assertions;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;



import java.util.List;
import java.util.function.Supplier;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


class CameraIntegartionTest
{


    @Test
    public void interSectionSphere1()
    {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sph= new Sphere(1,new Point3D(0,0,3));
        Ray _pix00=new Ray (camera.constructRayThroughPixel(3,3,0,0,1,6,6));
        Ray _pix01=new Ray (camera.constructRayThroughPixel(3,3,0,1,1,6,6));
        Ray _pix02=new Ray (camera.constructRayThroughPixel(3,3,0,2,1,6,6));
        Ray _pix10=new Ray (camera.constructRayThroughPixel(3,3,1,0,1,6,6));
        Ray _pix11=new Ray (camera.constructRayThroughPixel(3,3,1,1,1,6,6));
        Ray _pix12=new Ray (camera.constructRayThroughPixel(3,3,1,2,1,6,6));
        Ray _pix20=new Ray (camera.constructRayThroughPixel(3,3,2,0,1,6,6));
        Ray _pix21=new Ray (camera.constructRayThroughPixel(3,3,2,1,1,6,6));
        Ray _pix22=new Ray (camera.constructRayThroughPixel(3,3,2,2,1,6,6));

        int _count=0;
        if(!(sph.findIntersections(_pix00)==null))
         _count=sph.findIntersections(_pix00).size();

            if(!(sph.findIntersections(_pix01)==null))
        _count+=sph.findIntersections(_pix01).size();
        if(!(sph.findIntersections(_pix02)==null))

            _count+=sph.findIntersections(_pix02).size();
        if(!(sph.findIntersections(_pix10)==null))

            _count+=sph.findIntersections(_pix10).size();
        if(!(sph.findIntersections(_pix11)==null))

            _count+=sph.findIntersections(_pix11).size();
        if(!(sph.findIntersections(_pix12)==null))

            _count+=sph.findIntersections(_pix12).size();
        if(!(sph.findIntersections(_pix20)==null))

            _count+=sph.findIntersections(_pix20).size();
        if(!(sph.findIntersections(_pix21)==null))

            _count+=sph.findIntersections(_pix21).size();
        if(!(sph.findIntersections(_pix22)==null))

            _count+=sph.findIntersections(_pix22).size();

        Assertions.assertEquals(2,_count,"bad");
    }


    @Test
    public void interSectionSphere2()
    {
        Camera camera1 = new Camera(new Point3D(0,0,-0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sph1= new Sphere(40,new Point3D(0,0,41));

        Ray _pix00=new Ray (camera1.constructRayThroughPixel(3,3,0,0,1,6,6));
        int _count=0;
        if(!(sph1.findIntersections(_pix00)==null))
            _count=sph1.findIntersections(_pix00).size();
/**
        Ray _pix01=new Ray (camera.constructRayThroughPixel(3,3,0,1,1,6,6));
        Ray _pix02=new Ray (camera.constructRayThroughPixel(3,3,0,2,1,6,6));
        Ray _pix10=new Ray (camera.constructRayThroughPixel(3,3,1,0,1,6,6));
        Ray _pix11=new Ray (camera.constructRayThroughPixel(3,3,1,1,1,6,6));
        Ray _pix12=new Ray (camera.constructRayThroughPixel(3,3,1,2,1,6,6));
        Ray _pix20=new Ray (camera.constructRayThroughPixel(3,3,2,0,1,6,6));
        Ray _pix21=new Ray (camera.constructRayThroughPixel(3,3,2,1,1,6,6));
        Ray _pix22=new Ray (camera.constructRayThroughPixel(3,3,2,2,1,6,6));


System.out.println(_count);
        if(!(sph.findIntersections(_pix01)==null))
            _count+=sph.findIntersections(_pix01).size();
        if(!(sph.findIntersections(_pix02)==null))

            _count+=sph.findIntersections(_pix02).size();
        if(!(sph.findIntersections(_pix10)==null))

            _count+=sph.findIntersections(_pix10).size();
        if(!(sph.findIntersections(_pix11)==null))

            _count+=sph.findIntersections(_pix11).size();
        if(!(sph.findIntersections(_pix12)==null))

            _count+=sph.findIntersections(_pix12).size();
        if(!(sph.findIntersections(_pix20)==null))

            _count+=sph.findIntersections(_pix20).size();
        if(!(sph.findIntersections(_pix21)==null))

            _count+=sph.findIntersections(_pix21).size();
        if(!(sph.findIntersections(_pix22)==null))

            _count+=sph.findIntersections(_pix22).size();

        Assertions.assertEquals(18,_count,"bad");

 */
    }




}