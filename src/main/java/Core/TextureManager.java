package Core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextureManager {
	public Texture playerTextureStop;
	public Texture playerTextureRun;
	public Texture wallMid;
	BufferedImage wallMidBFI;
	BufferedImage[] playerStop;
	BufferedImage[] playerRun;
	
	BufferedImage pack1;
	public TextureManager() {
		init();
	}
	void init() {
		playerStop = new BufferedImage[4];
		playerRun = new BufferedImage[4];
		try {
			playerStop[0] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f0.png"));
			playerStop[1] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f1.png"));
			playerStop[2] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f2.png"));
			playerStop[3] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f3.png"));

			playerRun[0] = ImageIO.read(new File("res/frames/big_demon_run_anim_f0.png"));
			playerRun[1] = ImageIO.read(new File("res/frames/big_demon_run_anim_f1.png"));
			playerRun[2] = ImageIO.read(new File("res/frames/big_demon_run_anim_f2.png"));
			playerRun[3] = ImageIO.read(new File("res/frames/big_demon_run_anim_f3.png"));

			wallMidBFI = ImageIO.read(new File("res/frames/wall_mid.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerTextureStop = new Texture(16,16,playerStop);
		playerTextureRun = new Texture(16,16,playerRun);
		wallMid = new Texture(16,16,wallMidBFI);
	}
}