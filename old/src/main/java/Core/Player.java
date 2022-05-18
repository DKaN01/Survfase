package Core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

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
    public ArrayList<Tile> tilesColusion;
    public boolean board = false;
    int step = 0;


    public Player(Core core, Texture stop, Texture run)
    {
        this.core = core;
        this.kh = core.kh;
        this.run = run;
        this.stop = stop;
        tilesColusion = new ArrayList<>();
    }
    public void addTileColusion(Tile tile)
    {
        tilesColusion.add(tile);
    }
    public void jump()
    {
        if(step != 5)
        {
            if(step == 0){
                board = false;
                moveWorld(0,6);
                step++;
            }
            else {
                board = false;
                moveWorld(0,4);
                step++;
            }
        }
    }
    private void move()
    {
        if(kh.moveLeft) {
            moveWorld(-0.5f * speed, 0);
            needMirror = -1;
            playerRunner = true;
        }
        if(kh.moveRight) {
            moveWorld(0.5f * speed, 0);
            needMirror = 1;
            playerRunner = true;
        }
        if(kh.spacePress && board){
            jump();
        }
    }

    public void update()
    {
        if(step > 5)step = 0;
        if(!board)moveWorld(0.f,5.5f);
        for(Tile tile: tilesColusion)
        {
            if(getBounds().intersects(tile.getBoundse()))board = true;
        }
        if(kh.moveLeft || kh.moveRight || kh.spacePress)move();
        else playerRunner = false;
        speed = kh.playerSpeed;
    }

    public void moveWorld(float x, float y) {
        worldX += x;
        worldY += y;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)worldX-2,(int)worldY-2,stop.width+2,stop.height+2);
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
