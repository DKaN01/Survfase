package Core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MirrorImage {
    public static BufferedImage MirrorImage(BufferedImage defaultImage)
    {
        int width = defaultImage.getWidth();
        int height = defaultImage.getHeight();
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int j = 0; j < height; j++){
            for(int i = 0, w = width - 1; i < width; i++, w--){
                int p = defaultImage.getRGB(i, j);
                res.setRGB(w, j, p);
            }
        }
        return res;
    }
}
