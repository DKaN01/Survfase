package Core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureManager {
	public Texture playerTexture;
	public Texture road, water, grass, tree;
	
	BufferedImage pack1;
	public TextureManager() {
		init();
	}
	void init() {
		try {
			pack1 = ImageIO.read(new File("res/pack1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerTexture = new Texture(128,128,pack1.getSubimage(0, 80, 128, 128));
		road = new Texture(64,64,pack1.getSubimage(0, 16, 64, 64));
		tree = new Texture(64,64,pack1.getSubimage(64,16, 64, 64));
		water = new Texture(64,64,pack1.getSubimage(128, 16, 64, 64));
		grass = new Texture(64,64,pack1.getSubimage(192, 16, 64, 64));
	}
}