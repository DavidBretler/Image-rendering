//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Intersectable;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

class SuperSampellingTest {
    SuperSampellingTest() {
    }

    /**
     * sphere Triangle- Super Sampel
     * run time : 1 min 38 sec
     */
    @Test
    public void sphereTriangleSuperSampel() {
        Scene scene = new Scene("sphereTriangleInitial Super Sampel");
        scene.setCamera(new Camera(new Point3D(0.0D, 0.0D, -1000.0D), new Vector(0.0D, 0.0D, 1.0D), new Vector(0.0D, -1.0D, 0.0D)));
        scene.setDistance(1000.0D);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.0D));
        scene.addGeometries(
                new Sphere(
                        new Color(java.awt.Color.BLUE),
                        new Material(0.5D, 0.7D, 100), 60.0D,
                        new Point3D(0.0D, 0.0D, 200.0D)),
                new Triangle(
                        new Color(java.awt.Color.BLUE),
                        new Material(0.5D, 0.5D, 30,0.2,0.2),
                        new Point3D(-60.0D, 30.0D, 0.0D),
                        new Point3D(-30.0D, 60.0D, 0.0D),
                        new Point3D(-58.0D, 58.0D, 4.0D)));
        scene.addLights(new SpotLight(
                new Color(400.0D, 240.0D, 0.0D),
                new Point3D(-100.0D, 100.0D, -200.0D),
                new Vector(1.0D, -1.0D, 3.0D), 1.0D, 1.0E-5D, 1.5E-7D ));
        ImageWriter imageWriter = new ImageWriter("sphereTriangleSuperSampel", 200.0D, 200.0D, 5000, 5000);
        Render render = new Render(imageWriter, scene, 70, 1.25D);
        render.renderImage();
        render.writeToImage();
    }
    /*
    *//**
     *Sphere Triangle Super Sampeling And SoftShadow
     * run time : 14 min 56 sec
     *//*
    @Test
    public void SphereTriangleSuperSampelingAndSoftShadow() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0.0D, 0.0D, -1000.0D), new Vector(0.0D, 0.0D, 1.0D), new Vector(0.0D, -1.0D, 0.0D)));
        scene.setDistance(1000.0D);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.0D));
        scene.addGeometries(new Intersectable[]{new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), 60.0D, new Point3D(0.0D, 0.0D, 200.0D)), new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), new Point3D(-60.0D, 30.0D, 0.0D), new Point3D(-30.0D, 60.0D, 0.0D), new Point3D(-58.0D, 58.0D, 4.0D))});
        scene.addLights(new SpotLight(new Color(400.0D, 240.0D, 0.0D), new Point3D(-100.0D, 100.0D, -200.0D), new Vector(1.0D, -1.0D, 3.0D), 1.0D, 1.0E-5D, 1.5E-7D, 1.0D, 4.0D));
        ImageWriter imageWriter = new ImageWriter("SphereTriangleSuperSampelingAndSoftShadow", 200.0D, 200.0D, 400, 400);
        Render render = new Render(imageWriter, scene, 70, 1.25D);
        render.renderImage();
        render.writeToImage();
    }

    *//**
     * Sphere Triangle Soft Shadow
     * run time: 39 sec
     *//*
    @Test
    public void SphereTriangleSoftShadow() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0.0D, 0.0D, -1000.0D), new Vector(0.0D, 0.0D, 1.0D), new Vector(0.0D, -1.0D, 0.0D)));
        scene.setDistance(1000.0D);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.0D));
        scene.addGeometries(new Intersectable[]{new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), 60.0D, new Point3D(0.0D, 0.0D, 200.0D)), new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), new Point3D(-60.0D, 30.0D, 0.0D), new Point3D(-30.0D, 60.0D, 0.0D), new Point3D(-58.0D, 58.0D, 4.0D))});
        scene.addLights(new SpotLight(new Color(400.0D, 240.0D, 0.0D), new Point3D(-100.0D, 100.0D, -200.0D), new Vector(1.0D, -1.0D, 3.0D), 1.0D, 1.0E-5D, 1.5E-7D, 1.0D, 4.0D));
        ImageWriter imageWriter = new ImageWriter("SphereTriangleSoftShadow", 200.0D, 200.0D, 400, 400);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    *//**
     *Sphere Triangle NoEffect
     * run time: 6 sec 375
     *//*
    @Test
    public void SphereTriangleNoEffect() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0.0D, 0.0D, -1000.0D), new Vector(0.0D, 0.0D, 1.0D), new Vector(0.0D, -1.0D, 0.0D)));
        scene.setDistance(1000.0D);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.0D));
        scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), 60.0D, new Point3D(0.0D, 0.0D, 200.0D)), new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), new Point3D(-60.0D, 30.0D, 0.0D), new Point3D(-30.0D, 60.0D, 0.0D), new Point3D(-58.0D, 58.0D, 4.0D)));
        scene.addLights(new SpotLight(new Color(400.0D, 240.0D, 0.0D), new Point3D(-100.0D, 100.0D, -200.0D), new Vector(1.0D, -1.0D, 3.0D), 1.0D, 1.0E-5D, 1.5E-7D, 1.0D, 0.0D));
        ImageWriter imageWriter = new ImageWriter("SphereTriangleNoEffect", 200.0D, 200.0D, 400, 400);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }*/
    /**
     *Sphere Triangle NoEffect
     * run time: 2 sec
     */
    @Test
    public void NoEffect() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 1, 100, 0, 0), // sun
                        50, new Point3D(70, -70, 0)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.2, 1, 50, 0.55, 0), // inside sun1
                        30, new Point3D(60, -70, 0)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.2, 1, 100, 1, 0), // inside sun2
                        20, new Point3D(25, -70, 0)),

                new Sphere(new Color(java.awt.Color.white),new Material(0.5D, 0.5D, 30), //cloude
                        15, new Point3D(-47, -50, 0)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //cloude
                        15, new Point3D(-25 ,-35, 0)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //cloude
                        20, new Point3D(-17 ,-55, 0)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //cloude
                        70, new Point3D(-150 ,-150, 0)),

                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //shade
                        60, new Point3D(-200 ,-200, -200)),

                new Polygon(new Color(java.awt.Color.BLACK), new Material(1, 0.25, 5,0, 0.5) ,//tree
                        new Point3D(20, 120, 0), new Point3D(40, 120, 0), new Point3D(40, 50, 0),new Point3D(20, 50, 0)),
                new Triangle(new Color(java.awt.Color.green), new Material(0.5, 0.5, 300,0, 0.6), //
                        new Point3D(30, -25, 0),  new Point3D(0, 50, 0),new Point3D(60, 50, 0)), //

                new Polygon(new Color(java.awt.Color.red), new Material(0.5D, 0.5D, 30) ,//house
                        new Point3D(-80, 115, 0), new Point3D(-30, 115, 0), new Point3D(-30, 50, 0),new Point3D(-80, 50, 0)),
                new Triangle(Color.BLACK, new Material(0.5, 1, 200), //
                        new Point3D(-55, 10, -90),  new Point3D(-30, 50, 0),new Point3D(-80, 50, 0))

        );


        scene.addLights(new SpotLight(new Color(600, 400, 400), //shade
                new Point3D(-600 ,-600, -600), new Vector(1, -1, 1),
                1, 4E-5, 2E-7,1));
        scene.addLights(new SpotLight(new Color(600, 400, 400), //shade
                new Point3D(0 ,-700, -130), new Vector(0, 10, 3),
                1, 4E-5, 2E-7,1));


        scene.addLights(new SpotLight(new Color(400, 200, 200), //sun light
                new Point3D(10, -70, -300)  , new Vector(1, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("NoEffect", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     *Super Sampell
     * run time: 1 min 38 sec
     */
    @Test
    public void SuperSampell() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 1, 100, 0, 0), // sun
                        50, new Point3D(70, -70, 0)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.2, 1, 50, 0.55, 0), // inside sun1
                        30, new Point3D(60, -70, 0)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.2, 1, 100, 1, 0), // inside sun2
                        20, new Point3D(25, -70, 0)),

                new Sphere(new Color(java.awt.Color.white),new Material(0.5D, 0.5D, 30), //cloude
                        15, new Point3D(-47, -50, 0)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //cloude
                        15, new Point3D(-25 ,-35, 0)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //cloude
                        20, new Point3D(-17 ,-55, 0)),
                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //cloude
                        70, new Point3D(-150 ,-150, 0)),

                new Sphere(new Color(java.awt.Color.white), new Material(0.5D, 0.5D, 30), //shade
                        60, new Point3D(-200 ,-200, -200)),

                new Polygon(new Color(java.awt.Color.BLACK), new Material(1, 0.25, 5,0, 0.5) ,//tree
                        new Point3D(20, 120, 0), new Point3D(40, 120, 0), new Point3D(40, 50, 0),new Point3D(20, 50, 0)),
                new Triangle(new Color(java.awt.Color.green), new Material(0.5, 0.5, 300,0, 0.6), //
                        new Point3D(30, -25, 0),  new Point3D(0, 50, 0),new Point3D(60, 50, 0)), //

                new Polygon(new Color(java.awt.Color.red), new Material(0.5D, 0.5D, 30) ,//house
                        new Point3D(-80, 115, 0), new Point3D(-30, 115, 0), new Point3D(-30, 50, 0),new Point3D(-80, 50, 0)),
                new Triangle(Color.BLACK, new Material(0.5, 1, 200), //
                        new Point3D(-55, 10, -90),  new Point3D(-30, 50, 0),new Point3D(-80, 50, 0))

        );


        scene.addLights(new SpotLight(new Color(600, 400, 400), //shade
                new Point3D(-600 ,-600, -600), new Vector(1, -1, 1),
                1, 4E-5, 2E-7,1));
        scene.addLights(new SpotLight(new Color(600, 400, 400), //shade
                new Point3D(0 ,-700, -130), new Vector(0, 10, 3),
                1, 4E-5, 2E-7,1));



        scene.addLights(new SpotLight(new Color(400, 200, 200), //sun light
                new Point3D(10, -70, -300)  , new Vector(1, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("Adptive superSampel", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene,50,1.25).setMultithreading(3).setDebugPrint();

        render.renderImage();
        render.writeToImage();
    }


    /**
     * 3 polygons that create a corridor
     * with 3 sphere in the center diffrent transparency levels
     * and a triangle on top ,with reflection of the spheres  and the triangle on the polygons
     * shade from sphere on the polygon
     * 2 small shiny sphere in the bckg that reflect on the polygons

     * adaptive Super Sample
     * not adaptive super sample run time: 2 min 48 sec
     * adaptive super sample run time: 4 sec 535 ms
     */
    @Test
    public void finalscene1() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -20000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));


        scene.addGeometries( //

                new Sphere(new Color(80, 30, 170), new Material(0.2, 1, 20, 0, 0),
                        350,                        new Point3D(-0, 0, 1000)),


                new Sphere(new Color(80, 30, 170), new Material(0.2, 1, 20, 0.7, 0),
                        300,                        new Point3D(-0, 0, 500)),


                new Sphere(new Color(80, 30, 170), new Material(0.2, 1, 20, 0.9, 0),
                        250,                        new Point3D(-0, 0, 0)),


                new Sphere(new Color(255, 255, 255), new Material(0.2, 1, 110, 0, 0),
                        50,                        new Point3D(-1300, -1900, 2000)),
                new Sphere(new Color(255, 255, 255), new Material(0.2, 1, 110, 0, 0),
                        50,                        new Point3D(1300, -1900, 2000)),


                new Triangle( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,new Point3D(1000, -500, 2000),new Point3D(-1000, -500, 2000),new Point3D(0, 200, 2000)),

                new Triangle( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,new Point3D(1000, -500, 2000),new Point3D(-1000, -500, 2000),new Point3D(0, -4000, -2000)),


                new Polygon( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,//down surface
              new Point3D(-3000, 4000, -2000),  new Point3D(3000, 4000, -2000),new Point3D(1000, 200, 2000),new Point3D(-1000, 200, 2000)),

                new Polygon( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,////right surface
                        new Point3D(3000, 4000, -2000),  new Point3D(3000, -4000, -2000),new Point3D(1000, -500, 2000),new Point3D(1000, 200, 2000)),

        new Polygon( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,////left surface
                new Point3D(-3000, 4000, -2000),  new Point3D(-3000, -4000, -2000),new Point3D(-1000, -500, 2000),new Point3D(-1000, 200, 2000)));


        scene.addLights(
                new SpotLight(
                        new Color(1020, 400, 400),
                        new Point3D(-750, -750, 1900),
                        new Vector(-1, 1, 4),
                        1, 0.00001, 0.000005));
        scene.addLights(new SpotLight(new Color(600, 400, 400), //shade
                new Point3D(0 ,-1000, 1000), new Vector(0, 10, 0),
                1, 4E-5, 2E-7,1));

        scene.addLights(new PointLight(new Color(400, 200, 200), //sun light
                new Point3D(10, -1000, -13000)  , 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("finale scene 1", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene,50,1).setMultithreading(3);;

        render.renderImage();
        render.writeToImage();
    }


    /**
     * same scene with only diffrent z axis position to show effect of the proximity
     * 3 polygons that create a corridor
     * with 3 sphere in the center diffrent transparency levels
     * and a triangle on top ,with reflection of the spheres  and the triangle on the polygons
     * shade from sphere on the polygon
     *
     *  * 2 small shiny sphere in the bckg that reflect on the polygons
     * adaptive Super Sample
     * not adaptive super sample run time: 1 min 25 sec
     * adaptive super sample run time: 4 sec 525 ms
     */
    @Test
    public void finalscene2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -2000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(new Color(0, 0, 0));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));


        scene.addGeometries( //

                new Sphere(new Color(80, 30, 170), new Material(0.2, 1, 20, 0, 0), //
                        350,                        new Point3D(-0, 0, 100)),


                new Sphere(new Color(80, 30, 170), new Material(0.2, 1, 20, 0.7, 0), //
                        300,                        new Point3D(-0, 0, 50)),


                new Sphere(new Color(255, 255, 255), new Material(0.2, 1, 110, 0, 0),
                        50,                        new Point3D(-1300, -1900, 200)),
                new Sphere(new Color(255, 255, 255), new Material(0.2, 1, 110, 0, 0),
                        50,                        new Point3D(1300, -1900, 200)),


                new Sphere(new Color(80, 30, 170), new Material(0.2, 1, 20, 0.9, 0), //
                        250,                        new Point3D(-0, 0, 0)),

                new Triangle( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,new Point3D(1000, -500, 2000),new Point3D(-1000, -500, 2000),new Point3D(0, 200, 200)),

                new Triangle( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,new Point3D(1000, -500, 2000),new Point3D(-1000, -500, 2000),new Point3D(0, -4000, -200)),


                new Polygon( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,//down surface
                        new Point3D(-3000, 4000, -200),  new Point3D(3000, 4000, -200),new Point3D(1000, 200, 200),new Point3D(-1000, 200, 200)),

                new Polygon( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,////right surface
                        new Point3D(3000, 4000, -200),  new Point3D(3000, -4000, -200),new Point3D(1000, -500, 200),new Point3D(1000, 200, 200)),

                new Polygon( new Color(20, 20, 20), new Material(1, 0.25, 0,0, 0.8) ,////left surface
                        new Point3D(-3000, 4000, -200),  new Point3D(-3000, -4000, -200),new Point3D(-1000, -500, 200),new Point3D(-1000, 200, 200)));


        scene.addLights(
                new SpotLight(
                        new Color(1020, 400, 400),
                        new Point3D(-750, -750, 1900),
                        new Vector(-1, 1, 4),
                        1, 0.00001, 0.000005));
        scene.addLights(new SpotLight(new Color(600, 400, 400), //shade
                new Point3D(0 ,-1000, 100), new Vector(0, 10, 0),
                1, 4E-5, 2E-7,1));

        scene.addLights(new PointLight(new Color(400, 200, 200), //sun light
                new Point3D(10, -1000, -1300)  , 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("finale scene 2", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene,50,1).setMultithreading(3);;

        render.renderImage();
        render.writeToImage();
    }


}
