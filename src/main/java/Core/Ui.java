package Core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Ui {
	Core core;
	public Ui(Core core) {
		this.core = core;
	}
	void drawDebug(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial",Font.BOLD, 20));
		
		g2.drawString("PlayerWX:"+core.em.player.worldX, 0, 20);
		g2.drawString("PlayerWY:"+core.em.player.worldY, 0, 40);
		g2.drawString("PlayerSPEED:"+core.em.player.speed, 0, 60);
		g2.drawString("blockSize:"+core.kh.Mashab,0,80);
        g2.drawString("Zombies:"+core.zombies,0,100);
	}
	public void draw(Graphics2D g2) {
		if(core.kh.debug)drawDebug(g2);
	}
}
