package Core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
	public float screenX, screenY;
	public float worldX, worldY;
	public Texture texture;
	public Thread updaterFrame;
	private int mirror = 1;
	public int needMirror = 1;
	
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
	public void moveWorld(float x, float y) {
		worldX += x;
		worldY += y;
	}
	public void moveScreen(float x, float y) {
		screenX += x;
		screenY += y;
	}
	public BufferedImage getImage()
	{
		if(mirror > needMirror)return MirrorImage.MirrorImage(texture.getTexture());
		if(mirror < needMirror)return MirrorImage.MirrorImage(texture.getTexture());
		else return texture.getTexture();
	}
	public void startUpdaterFrame()
	{
		this.updaterFrame = new Thread(this::updater);
		updaterFrame.start();
	}




    public Rectangle getBounds()
    {
        return new Rectangle((int)worldX,(int)worldY,texture.width,texture.height);
    }



	private void updater()
	{
		while(this != null)
		{
			texture.updateFrame();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
