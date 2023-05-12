package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

public class ImageWriterTest {
    /**
     * First test - ImageWriterTest (800x500) resolution .
     */
    @Test
    void testImage() {
        Color color = new Color(153, 255, 204);
        ImageWriter imageWriter = new ImageWriter("First image", 800, 500);
        for (int i = 0; i < 800; ++i) {
            for (int j = 0; j < 500; ++j) {
                if (i % 50 == 0 || j % 50 == 0) // 16x10 - ViewPlane - Grid .
                    imageWriter.writePixel(i, j, Color.BLACK);
                else
                    imageWriter.writePixel(i, j, color);
            }
        }
        imageWriter.writeToImage();
    }
}
