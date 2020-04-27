package elements;

import geometries.Plane;
import geometries.Sphere;

import geometries.Triangle;
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
        Sphere sph =  new Sphere(2.5, new Point3D(0, 0, 2.5));
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(18,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void interSectionSphere3()
    {
        Sphere sph =  new Sphere(2d, new Point3D(0, 0, 2d));
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(10,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void interSectionSphere4()
    {
        Sphere sph =  new Sphere(4d, new Point3D(0, 0, 2d));
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void interSectionSphere5()
    {
        Sphere sph =  new Sphere(2d, new Point3D(0, 0, -5));
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(0,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void interSectionPlane1()
    {
        Plane pl=new Plane(new Point3D(0, 0, 6),new Point3D(1, 1, 6),new Point3D(-5, -765, 6));
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }
    @Test
    public void interSectionPlane2()
    {
        Plane pl=new Plane(new Point3D(0, 0, 5),new Point3D(10, 10, 4),new Point3D(4, 10, 4));
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void interSectionPlane3()
    {
        Plane pl=new Plane(new Point3D(0, 0, 5),new Point3D(1, 1, 4),new Point3D(4, 10, 3));
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(6,count,"too bad");
        System.out.println("count: "+count);
    }

    @Test
    public void interSectionTrinale1()
    {
     Triangle tri=new Triangle (new Point3D(0, -1, 2),new Point3D(1, 1, 2),new Point3D(-1, 1, 2));
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = tri.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(1,count,"too bad");
        System.out.println("count: "+count);
    }
    @Test
    public void interSectionTrinale2()
    {
        Triangle tri=new Triangle (new Point3D(0, -20, 2),new Point3D(1, 1, 2),new Point3D(-1, 1, 2));
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = tri.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(2,count,"too bad");
        System.out.println("count: "+count);
    }
    @Test
    public void interSectionTrinale3()
    {
        Triangle tri=new Triangle (new Point3D(0, -10, 2),new Point3D(10, 10, 2),new Point3D(-10, 10, 2));
        Camera cam = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = tri.findIntersections(cam.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }
}