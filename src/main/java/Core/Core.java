package Core;

import java.awt.Graphics2D;

import Window.*;

public class Core {
	public Panel panel;
	public Window win;
	public Keyhandler kh;
	public EntityManager em;
	public Map map;
	public Ui ui;
	public TextureManager tm;
	
	boolean game = true;
	
	public Core(Panel panel, Window win, Keyhandler kh) {
		this.win = win;
		this.panel = panel;
		this.tm = new TextureManager();
		this.kh = kh;
		this.map = new Map(tm);
		this.em = new EntityManager(map, panel, this, kh, tm);
		this.ui = new Ui(this);
		map.setPlayer(em.player);
		
		setupGame();
	}
	
	void setupGame() {
		panel.setCore(this);
		game();
	}
	void update() {
		em.update();
	}
	
	void game() {
		while(game) {
			update();
			panel.repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		map.draw(g2);
		em.draw(g2);
		ui.draw(g2);
	}
}
