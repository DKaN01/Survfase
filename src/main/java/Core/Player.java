package Core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    Keyhandler kh;
    Core core;
    Texture stop, run;




    boolean playerRunner = false;
    public float speed = 3f;
    public float worldX = 400, worldY = 400;
    public int screenX = (1280/2) - 24;
    public int screenY = (720/2) - 24;
    int mirror = 1;
    public int needMirror = 1;
    public Thread updaterFrame;





    public Player(Core core, Texture stop, Texture run)
    {
        this.core = core;
        this.kh = core.kh;
        this.run = run;
        this.stop = stop;
    }




    private void move()
    {
        if(kh.moveUp) {
            moveWorld(0, -0.5f*speed);
            playerRunner = true;
        }
        if(kh.moveLeft) {
            moveWorld(-0.5f * speed, 0);
            needMirror = -1;
            playerRunner = true;
        }
        if(kh.moveRight) {
            moveWorld(0.5f*speed, 0);
            needMirror = 1;
            playerRunner = true;
        }
        if(kh.moveDown) {
            moveWorld(0, 0.5f*speed);
            playerRunner = true;
        }
    }




    public void update()
    {
        if(kh.moveUp || kh.moveDown || kh.moveLeft || kh.moveRight)move();
        else playerRunner = false;
        speed = kh.playerSpeed;
    }




    public void moveWorld(float x, float y) {
        worldX += x;
        worldY += y;
    }


    public Rectangle getBounds()
    {
        return new Rectangle((int)worldX,(int)worldY,stop.width,stop.height);
    }

    public BufferedImage getImage()
    {
        if(playerRunner)
        {
            if(needMirror != mirror)return MirrorImage.MirrorImage(run.getTexture());
            else return run.getTexture();
        }
        else return stop.getTexture();
    }




    public void startUpdaterFrame()
    {
        this.updaterFrame = new Thread(this::updater);
        updaterFrame.start();
    }




    private void updater()
    {
        while(updaterFrame != null)
        {
            stop.updateFrame();
            run.updateFrame();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
