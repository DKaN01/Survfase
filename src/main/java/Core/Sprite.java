package Core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
	public float screenX, screenY;
	public float worldX, worldY;
	public Texture texture;
	public Rectangle rect;
	
	public Sprite(int screenX, int screenY, Texture texture) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.texture = texture; 
		
		init();
	}
	public Sprite(int screenX, int screenY, Texture texture, float worldX, float worldY) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.texture = texture; 
		this.worldX = worldX;
		this.worldY = worldY;
		
		init();
	}
	public Sprite(Texture texture) {
		this.texture = texture;
	}
	void init() {}
	BufferedImage getImg() {
		return texture.texture;
	}
	public void moveWorld(float x, float y) {
		worldX += x;
		worldY += y;
	}
	public void moveScreen(float x, float y) {
		screenX += x;
		screenY += y;
	}
}
