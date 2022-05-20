package Core;

import java.awt.Graphics2D;
import java.util.*;

import Window.Panel;

public class EntityManager {
	Map map;
	Panel panel;
	Core core;
	Keyhandler kh;
	TextureManager tm;
	Player player;
    ArrayList<Entity> zombies;

	public EntityManager(Map map, Panel panel, Core core, Keyhandler kh, TextureManager tm) {
		this.map = map;
		this.panel = panel;
		this.core = core;
		this.kh = kh;
		this.tm = tm;
        zombies = new ArrayList<>();


		init();
	}



	void init() {
		player = new Player(core, tm.playerTextureStop, tm.playerTextureRun);
		player.startUpdaterFrame();
	}


	public void draw(Graphics2D g2) {
		g2.drawImage(player.getImage(), player.screenX, player.screenY, player.stop.width,player.stop.height,null);
        try{
            for(Entity zomb : zombies)
            {
                g2.drawImage(zomb.getImg(), zomb.screenX, zomb.screenY, null);
            }
        }
        catch(Exception ex){}
	}




	public void update() {
		player.update();
        core.zombies = zombies.size();
        if(new Random().nextInt(1000) == 1 )
        {
            Entity temp = new Entity(new Random().nextInt(100),new Random().nextInt(100),tm.zombieIdle,tm.zombieRun, core);
            temp.startFrameThread();
            zombies.add(temp);
        }
        try{
            for(Entity zomb : zombies)
            {
                zomb.screenX = (int)zomb.worldX - (int)player.worldX + player.screenX;
                zomb.screenY = (int)zomb.worldY - (int)player.worldY + player.screenY;
                if(player.getBounds((int)player.worldX,(int)player.worldY,player.width, player.height).intersects(zomb.getBounds()))zombies.remove(zomb);
            }
        }
        catch(Exception ex){}
	}




}
