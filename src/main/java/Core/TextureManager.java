package Core;

import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class TextureManager {
	public Texture playerTextureStop;
	public Texture playerTextureRun;


    public Texture zombieIdle, zombieRun;
    public BufferedImage[] zI, zR;


	public Texture wallMid, floor_1;



	BufferedImage wallMidBFI, floor_1BFI;
	BufferedImage[] playerStop;
	BufferedImage[] playerRun;

	public TextureManager() {
		init();
	}
	void init() {
		playerStop = new BufferedImage[4];
		playerRun = new BufferedImage[4];
        zI = new BufferedImage[4];
        zR = new BufferedImage[4];
		try {
			playerStop[0] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f0.png"));
			playerStop[1] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f1.png"));
			playerStop[2] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f2.png"));
			playerStop[3] = ImageIO.read(new File("res/frames/big_demon_idle_anim_f3.png"));

			playerRun[0] = ImageIO.read(new File("res/frames/big_demon_run_anim_f0.png"));
			playerRun[1] = ImageIO.read(new File("res/frames/big_demon_run_anim_f1.png"));
			playerRun[2] = ImageIO.read(new File("res/frames/big_demon_run_anim_f2.png"));
			playerRun[3] = ImageIO.read(new File("res/frames/big_demon_run_anim_f3.png"));

            for(int i = 0; i< zI.length; i++)zI[i] = ImageIO.read(new File("res/frames/big_zombie_idle_anim_f"+i+".png"));
            for(int i = 0; i< zR.length; i++)zR[i] = ImageIO.read(new File("res/frames/big_zombie_run_anim_f"+i+".png"));

			wallMidBFI = ImageIO.read(new File("res/frames/wall_mid.png"));
			floor_1BFI = ImageIO.read(new File("res/frames/floor_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerTextureStop = new Texture(32,36,playerStop);
		playerTextureRun = new Texture(32,36,playerRun);
        zombieRun = new Texture(32,34,zR);
        zombieIdle = new Texture(32,34,zI);
		wallMid = new Texture(16,16,wallMidBFI);
		floor_1 = new Texture(16,16,floor_1BFI);
	}
}