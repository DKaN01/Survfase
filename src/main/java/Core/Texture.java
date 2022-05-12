package Core;

import java.awt.image.BufferedImage;

public class Texture {
	public int width, height;
	public BufferedImage texture;
	public Texture(int width, int height, BufferedImage texture) {
		this.width = width;
		this.height = height;
		this.texture = texture;
	}
}