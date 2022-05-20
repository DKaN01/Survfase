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
    public boolean stopColusionWithBlock = false;


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
    private void move()
    {
        if(kh.moveUp) {
            if(!stopColusionWithBlock){
                moveWorld(0, -0.5f * speed);
                playerRunner = true;
            }
            else moveWorld(0,10.5f);
        }
        if(kh.moveLeft) {
            if(!stopColusionWithBlock){
                moveWorld(-0.5f * speed, 0);
                needMirror = -1;
                playerRunner = true;
            }
            else moveWorld(10.5f,0);
        }
        if(kh.moveRight) {
            if(!stopColusionWithBlock){
                moveWorld(0.5f * speed, 0);
                needMirror = 1;
                playerRunner = true;
            }
            else moveWorld(-10.5f,0);
        }
        if(kh.moveDown) {
            if(!stopColusionWithBlock){
                moveWorld(0, 0.5f*speed);
                playerRunner = true;
            }
            else moveWorld(0,-10.5f);
        }
    }

    public void update()
    {
        for(Tile tile: tilesColusion)
        {
            if(getBounds().intersects(tile.getBoundse())){
                stopColusionWithBlock = true;
                System.out.println("Colusion detect");
            }
        }
        if(kh.moveUp || kh.moveDown || kh.moveLeft || kh.moveRight)move();
        else playerRunner = false;
        speed = kh.playerSpeed;
        stopColusionWithBlock = false;
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
