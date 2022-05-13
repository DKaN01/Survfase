package Core;

import java.awt.image.BufferedImage;

public class Texture {
	public int width, height;
	public BufferedImage texture;
	public BufferedImage[] textures;
	public int frame = 0;

	public Texture(int width, int height, BufferedImage texture) {
		this.width = width;
		this.height = height;
		this.texture = texture;
	}
	public Texture(int width, int height, BufferedImage[] textures) {
		this.width = width;
		this.height = height;
		this.textures = textures;
	}

	public void updateFrame()
	{
		if(frame < textures.length - 1)frame++;
		else frame = 0;
	}
	public BufferedImage getTexture()
	{
		if(textures != null) return textures[frame];
		else return texture;
	}
}