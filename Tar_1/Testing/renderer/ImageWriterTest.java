package renderer;

import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {




    @Test
    void ImageWiterWriteToImageTest() {
        ImageWriter imageWriter = new ImageWriter("Image Witer Write To Image Test", 1600, 1000, 800, 500);
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(j, i, Color.black);
                } else {
                    imageWriter.writePixel(j, i, Color.orange);
                }
            }
        }
        imageWriter.writeToImage();
    }
}