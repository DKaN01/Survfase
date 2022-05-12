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
	
	Sprite player;
	
	public float speed = 3f;
	
	public EntityManager(Map map, Panel panel, Core core, Keyhandler kh, TextureManager tm) {
		this.map = map;
		this.panel = panel;
		this.core = core;
		this.kh = kh;
		this.tm = tm;
		
		init();
	}
	void init() {
		player = new Sprite(1280/2 - 32, 720/2 - 32,tm.playerTexture, 1000,1000);
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(player.getImg(), (int)player.screenX, (int)player.screenY, 48,48,null);
	}
	public void update() {		
		if(kh.moveUp) {
			player.moveWorld(0, -0.5f*speed);
		}
		if(kh.moveLeft) {
			player.moveWorld(-0.5f*speed, 0);
		}
		if(kh.moveRight) {
			player.moveWorld(0.5f*speed, 0);
		}
		if(kh.moveDown) {
			player.moveWorld(0, 0.5f*speed);
		}
		speed = kh.playerSpeed;
	}
}
