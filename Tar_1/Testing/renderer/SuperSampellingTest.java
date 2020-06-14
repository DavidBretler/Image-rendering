//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Intersectable;
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
        scene.addGeometries(new Intersectable[]{new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), 60.0D, new Point3D(0.0D, 0.0D, 200.0D)), new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), new Point3D(-60.0D, 30.0D, 0.0D), new Point3D(-30.0D, 60.0D, 0.0D), new Point3D(-58.0D, 58.0D, 4.0D))});
        scene.addLights(new SpotLight(new Color(400.0D, 240.0D, 0.0D), new Point3D(-100.0D, 100.0D, -200.0D), new Vector(1.0D, -1.0D, 3.0D), 1.0D, 1.0E-5D, 1.5E-7D, 1.0D, 0.0D));
        ImageWriter imageWriter = new ImageWriter("sphereTriangleSuperSampel", 200.0D, 200.0D, 400, 400);
        Render render = new Render(imageWriter, scene, 70, 1.25D);
        render.renderImage();
        render.writeToImage();
    }

    /**
     *Sphere Triangle Super Sampeling And SoftShadow
     * run time : 14 min 56 sec
     */
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

    /**
     * Sphere Triangle Soft Shadow
     * run time: 39 sec
     */
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

    /**
     *Sphere Triangle NoEffect
     * run time: 6 sec 375
     */
    @Test
    public void SphereTriangleNoEffect() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0.0D, 0.0D, -1000.0D), new Vector(0.0D, 0.0D, 1.0D), new Vector(0.0D, -1.0D, 0.0D)));
        scene.setDistance(1000.0D);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0.0D));
        scene.addGeometries(new Intersectable[]{new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), 60.0D, new Point3D(0.0D, 0.0D, 200.0D)), new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5D, 0.5D, 30), new Point3D(-60.0D, 30.0D, 0.0D), new Point3D(-30.0D, 60.0D, 0.0D), new Point3D(-58.0D, 58.0D, 4.0D))});
        scene.addLights(new SpotLight(new Color(400.0D, 240.0D, 0.0D), new Point3D(-100.0D, 100.0D, -200.0D), new Vector(1.0D, -1.0D, 3.0D), 1.0D, 1.0E-5D, 1.5E-7D, 1.0D, 0.0D));
        ImageWriter imageWriter = new ImageWriter("SphereTriangleNoEffect", 200.0D, 200.0D, 400, 400);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}
