package renderer;

/**
 *
 */
import geometries.Polygon;
import org.junit.jupiter.api.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 *
 */
public class ReflectionRefractionTests {

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
                        new Point3D(0, 0, 50)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors()
    {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

            scene.addGeometries(
                    new Sphere(
                            new Color(0, 0, 100),
                            new Material(0.25, 0.25, 20, 0.5, 0),
                            400,
                            new Point3D(-950, 900, 1000)),
                    new Sphere(
                            new Color(100, 20, 20),
                            new Material(0.25, 0.25, 20),
                            200,
                            new Point3D(-950, 900, 1000)),
                    new Triangle(
                            new Color(20, 20, 20),
                            new Material(0, 0, 0, 0, 1),
                            new Point3D(1500, 1500, 1500),
                            new Point3D(-1500, -1500, 1500),
                            new Point3D(670, -670, -3000)),
                    new Triangle(
                            new Color(20, 20, 20),
                            new Material(0, 0, 0, 0, 0.5),
                            new Point3D(1500, 1500, 1500),
                            new Point3D(-1500, -1500, 1500),
                            new Point3D(-1500, 1500, 2000)));

            scene.addLights(
                    new SpotLight(
                            new Color(1020, 400, 400),
                            new Point3D(-750, 750, 150),
                            new Vector(-1, 1, 4),
                            1, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }


    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles one of them a miriror lighted by a spot light with a partially transparent Sphere  producing  shadow
     * and a  regular sphere with high diffuse parameter producing reflation
     */

    @Test
    public void allefectTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0, 0, 0, 0, 0.2), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        28, new Point3D(55, -10, 50)),
                new Sphere(new Color(java.awt.Color.red), new Material(1, 0.25, 5,0.3, 0), // )
                        25, new Point3D(-30, -30, 50)));


        scene.addLights(new SpotLight(new Color(600, 400, 400), //
                new Point3D(50, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("allefectTest", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test

    public void allefectTest10sheapBounos() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(20,20,200));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
               new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-55, 10, 130),  new Point3D(-30, 49, 115),new Point3D(-80, 49, 115)), //
                 new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 0, 0), // sun
                        50, new Point3D(70, -70, 0)),
                new Sphere(new Color(java.awt.Color.orange), new Material(0.2, 0.2, 70, 0.5, 0.8), // inside sun1
                        30, new Point3D(70, -70, -10)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.2, 0.2, 50, 0.9, 0), // inside sun2
                        20, new Point3D(70, -70, -30)),

                new Sphere(new Color(java.awt.Color.white), new Material(0.2, 0.2, 50, 0.9, 0), //cloude
                        20, new Point3D(-70, -60, -30)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.2, 0.2, 50, 0.9, 0), //cloude
                        20, new Point3D(-60 ,-50, -30)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.2, 0.2, 50, 0.9, 0), //cloude
                        20, new Point3D(-40 ,-65, -30)),

                new Polygon(new Color(java.awt.Color.BLACK), new Material(1, 0.25, 5,0.3, 0) ,
                        new Point3D(20, 110, 115), new Point3D(40, 110, 115), new Point3D(40, 50, 115),new Point3D(20, 50, 115)),

                new Triangle(new Color(java.awt.Color.green), new Material(0.5, 0.5, 60), //
                        new Point3D(30, -20, 130),  new Point3D(15, 50, 115),new Point3D(45, 50, 115)), //
                new Polygon(new Color(java.awt.Color.red), new Material(1, 0.25, 5,0.3, 0) ,
                        new Point3D(-80, 110, 115), new Point3D(-30, 110, 115), new Point3D(-30, 50, 115),new Point3D(-80, 50, 115)));




        scene.addLights(new SpotLight(new Color(600, 400, 400), //
                new Point3D(50, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("bonusTest", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}
