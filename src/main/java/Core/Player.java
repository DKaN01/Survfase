package Core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Player {
    Keyhandler kh;
    Core core;
    Texture stop, run;
    public int width, height;

    boolean playerRunner = false;
    public float speed = 3f;
    public float worldX = 400, worldY = 400;
    public int screenX = (1280/2) - 24;
    public int screenY = (720/2) - 24;
    int mirror = 1;
    public int needMirror = 1;
    public Thread updaterFrame;
    public ArrayList<Tile> tilesColusion;
    public boolean colUp, colDown, colRight, colLeft;


    public Player(Core core, Texture stop, Texture run)
    {
        this.core = core;
        this.kh = core.kh;
        this.run = run;
        this.stop = stop;
        this.width = run.width;
        this.height = run.height;
        tilesColusion = new ArrayList<>();
    }
    public void addTileColusion(Tile tile)
    {
        tilesColusion.add(tile);
    }
    private void move()
    {
        if(!colUp && kh.moveUp)
        {
            moveWorld(0,-0.5f * speed);
            playerRunner = true;
        }
        if(!colDown && kh.moveDown)
        {
            moveWorld(0,0.5f*speed);
        }

        if(!colRight && kh.moveRight)
        {
            moveWorld(0.5f * speed, 0);
            needMirror = 1;
            playerRunner = true;
        }
        if(!colLeft && kh.moveLeft)
        {
            moveWorld(-0.5f * speed, 0);
            needMirror = -1;
            playerRunner = true;
        }
    }

    public void update()
    {
        for(Tile tile : tilesColusion)
        {
            colUp = getBounds((int)worldX,(int)worldY,width,1).intersects(tile.getBounds());
            colDown = getBounds((int)worldX,(int) worldY + height,width,0).intersects(tile.getBounds());
            colRight = getBounds((int) worldX + width,(int)worldY,0,height).intersects(tile.getBounds());
            colLeft = getBounds((int)worldX,(int) worldY,1,height).intersects(tile.getBounds());
        }
        if(kh.moveUp || kh.moveDown || kh.moveLeft || kh.moveRight)move();
        else playerRunner = false;
        speed = kh.playerSpeed;
    }

    public void moveWorld(float x, float y) {
        worldX += x;
        worldY += y;
    }

    public Rectangle getBounds(int x, int y, int width, int height)
    {
        return new Rectangle(x,y,width,height);
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
