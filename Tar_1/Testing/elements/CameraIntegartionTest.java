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
    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

    /**
     * 3X3 center(1,1) ,find the number of  interSection point of a ray from camera and a sphere
     * excepted result : 2 because of the size of the sphere
     */
    @Test
    public void interSectionSphere1() {
        Sphere sph = new Sphere(1, new Point3D(0, 0, 3));
//        Ray ray = cam1.constructRayThroughPixel(3,3,0,0,1,3,3);
//        List<Point3D> results =  sph.findIntersections(ray);
        List<Point3D> results;
        int count = 0;
        int Nx = 3; //number of pixel in x line
        int Ny = 3;// number of pixel in y line
        for (int i = 0; i < 3; ++i) {  // go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(2, count, "too bad");
        System.out.println("count: " + count);
    }
    /**
     * 3X3 all view plane  ,find the number of  interSection point of a ray from camera and a sphere
     * excepted result : 18 because of the size of the sphere
     */
    @Test
    public void interSectionSphere2()
    {
        Sphere sph =  new Sphere(2.5, new Point3D(0, 0, 2.5));
        List<Point3D> results;
        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(18,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3 center row and column ,find the number of  interSection point of a ray from camera and a sphere
     * excepted result : 10 because of the size of the sphere
     */
    @Test
    public void interSectionSphere3()
    {
        Sphere sph =  new Sphere(2d, new Point3D(0, 0, 2d));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(10,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3  ,find the number of  interSection point of a ray from camera and a sphere
     * excepted result : 10 because of the size of the sphere ,camera in inside the sphere
     */
    @Test
    public void interSectionSphere4()
    {
        Sphere sph =  new Sphere(4d, new Point3D(0, 0, 2d));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     *3X3  , find the number of  interSection point of a ray from camera and a sphere
     * excepted result : 0 because of the location of the sphere behind the camera
     */
    @Test
    public void interSectionSphere5()
    {
        Sphere sph =  new Sphere(2d, new Point3D(0, 0, -5));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(0,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3  all view plane ,find the number of  interSection point of a ray from camera and a plane
     * excepted result : 9 because of the location of the plane
     */
    @Test
    public void interSectionPlane1()
    {
        Plane pl=new Plane(new Point3D(0, 0, 6),new Point3D(1, 1, 6),new Point3D(-5, -765, 6));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3 all view plane  ,find the number of  interSection point of a ray from camera and a plane
     * excepted result : 9 because of the location of the plane ,lining but not enough to change number of interSection point
     */
    @Test
    public void interSectionPlane2()
    {
        Plane pl=new Plane(new Point3D(0, 0, 5),new Point3D(10, 10, 4),new Point3D(4, 10, 4));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3 top and middle row  ,find the number of  interSection point of a ray from camera and a plane
     * excepted result : 6 because of the location of the plane ,lining  enough to change number of interSection point
     */
    @Test
    public void interSectionPlane3()
    {
        Plane pl=new Plane(new Point3D(0, 0, 5),new Point3D(1, 1, 4),new Point3D(4, 10, 3));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = pl.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(6,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3 center(1,1) ,find the number of  interSection point of a ray from camera and a triangle
     * excepted result : 2 because of the location and size of the triangle
     */
    @Test
    public void interSectionTrinale1()
    {
        Triangle tri=new Triangle (new Point3D(0, -1, 2),new Point3D(1, 1, 2),new Point3D(-1, 1, 2));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = tri.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(1,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3 center(1,1) and (0,1) ,find the number of  interSection point of a ray from camera and a triangle
     * excepted result : 3 because of the location and size of the triangle , the triangle is higher
     */
    @Test
    public void interSectionTrinale2()
    {
        List<Point3D> results;
        Triangle tri=new Triangle (new Point3D(1, 1, 2),new Point3D(-1, 1, 2),new Point3D(0, -20, 2));
        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = tri.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(2,count,"too bad");
        System.out.println("count: "+count);
    }
    /**
     * 3X3 all view plane ,find the number of  interSection point of a ray from camera and a triangle
     * excepted result : 9 because of the location and size of the triangle , the triangle is very big
     */
    @Test
    public void interSectionTrinale3()
    {
        Triangle tri=new Triangle (new Point3D(0, -10, 2),new Point3D(10, 10, 2),new Point3D(-10, 10, 2));
        List<Point3D> results;

        int count = 0;
        // TODO explanations
        int Nx =3;//number of pixel in x line
        int Ny =3;// number of pixel in y line

        // TODO explanations
        for (int i = 0; i < 3; ++i) {// go over all the pixels like a 2 Dimension array
            for (int j = 0; j < 3; ++j) {
                results = tri.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(9,count,"too bad");
        System.out.println("count: "+count);
    }
}