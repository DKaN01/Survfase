package Core;

import java.awt.*;
import java.awt.image.*;

public class Entity{
    public float worldX, worldY;
    public int screenX, screenY;
    public Texture textureI;
    public Texture textureR;
    public Thread frameUpdate;
    int mirror = 0;
    public int needMirror = 0;
    public boolean runner = false;
    Core core;



    public Entity(int worldX, int worldY, Texture textureI, Texture textureR, Core core)
    {
        this.textureI = textureI;
        this.textureR = textureR;
        this.worldX = worldX;
        this.worldY = worldY;
        this.core = core;
    }

    public float getWorldX()
    {
        return worldX;
    }
    public float getWorldY()
    {
        return worldY;
    }

    public BufferedImage getImg()
    {
        if(runner)
        {
            if(needMirror != mirror)return MirrorImage.MirrorImage(textureR.getTexture());
            else return textureR.getTexture();
        }
        else return textureI.getTexture();
    }

    public Rectangle getBounds(int x, int y, int width, int height){return new Rectangle(x,y,width,height);}
    public Rectangle getBounds(){return new Rectangle((int)worldX, (int)worldY, textureR.width, textureR.height);}

    public void move(int x, int y)
    {
        worldX += x;
        worldY += y;
    }

    public void startFrameThread()
    {
        frameUpdate = new Thread(this::frameUpdater);
        frameUpdate.start();
    }
    void frameUpdater()
    {
        synchronized(core.sync){
            while(frameUpdate != null){
                textureI.updateFrame();
                textureR.updateFrame();
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
