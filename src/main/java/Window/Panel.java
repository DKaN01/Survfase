package Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Core.Core;
import Core.Keyhandler;

public class Panel extends JPanel{
	public int tileSize, maxScreenRow, maxScreenCol,height,width;
	
	public Core core;
	public Panel(int tileSize, int maxScreenCol, int maxScreenRow, Keyhandler kh) {
		this.tileSize = tileSize;
		this.maxScreenCol = maxScreenCol;
		this.maxScreenRow = maxScreenRow;
		this.width = tileSize*maxScreenCol;
		this.height = tileSize*maxScreenRow;
		this.addKeyListener(kh);
		this.setFocusable(true);

		this.setDoubleBuffered(true);
		this.setBackground(Color.BLACK);
		setup();
	}
	void setup() {
		this.setPreferredSize(new Dimension(width,height));
	}
	public void setCore(Core core) {
		this.core = core;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(core != null)core.draw(g2);
	}
}
