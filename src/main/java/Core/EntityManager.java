package Core;

import java.awt.Color;
import java.awt.Graphics2D;

import Window.Panel;

public class EntityManager {
	Map map;
	Panel panel;
	Core core;
	Keyhandler kh;
	TextureManager tm;

	Player player;
	
	public EntityManager(Map map, Panel panel, Core core, Keyhandler kh, TextureManager tm) {
		this.map = map;
		this.panel = panel;
		this.core = core;
		this.kh = kh;
		this.tm = tm;
		
		init();
	}
	void init() {
//		player = new Sprite(1280/2 - 32, 720/2 - 32,tm.playerTextureStop, playerWorldX,playerWorldY);
//		player.startUpdaterFrame();
//		playerRun = new Sprite(1280/2 - 32, 720/2 - 32,tm.playerTextureRun, playerWorldX,playerWorldY);
//		playerRun.startUpdaterFrame();
		player = new Player(core, tm.playerTextureStop, tm.playerTextureRun);
		player.startUpdaterFrame();
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(player.getImage(), player.screenX, player.screenY, player.stop.width,player.stop.height,null);
	}
	public void update() {
		player.update();
	}
}
