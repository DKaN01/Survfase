package Core;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	Sprite[] types;
	Sprite[][] tiles;
	Sprite player;
	
	final int maxBlockRow = 50;
	final int maxBlockCol = 50;
	final int blockSizeDefault = 64;
	
	public Map(TextureManager tm) {
		tiles = new Sprite[maxBlockCol][maxBlockCol];
		
		types = new Sprite[4];
		types[0] = new Sprite(tm.tree);
		types[1] = new Sprite(tm.road);
		types[2] = new Sprite(tm.tree);
		types[3] = new Sprite(tm.water);
		init();
	}
	public void setPlayer(Sprite player) {
		this.player = player;
	}
	void init() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("res/map1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int worldCol = 0;
		int worldRow = 0;
		while(worldCol < maxBlockCol && worldRow < maxBlockRow) {
			String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            while(worldCol < maxBlockCol) {
            	String[] numbers = line.split(" ");
                int num = Integer.parseInt(numbers[worldCol]);
                tiles[worldCol][worldRow] = types[num];
                worldCol++;
                System.out.println("Col: "+worldCol);
                System.out.println("Row: "+worldRow);
            }
			if(worldCol == maxBlockCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
	public void update() {
		
	}
	public void draw(Graphics2D g2) {
		if(player != null) {
			int worldCol = 0;
			int worldRow = 0;
			
			while(worldCol < maxBlockCol && worldRow < maxBlockRow) {
				
				int worldX = worldCol * blockSizeDefault;
				int worldY = worldRow * blockSizeDefault;
				int screenX = worldX - (int)player.worldX + (int)player.screenX;
				int screenY = worldY - (int)player.worldY + (int)player.screenY;
				
				g2.drawImage(tiles[worldCol][worldRow].getImg(), screenX, screenY,blockSizeDefault,blockSizeDefault,null);
				
				
				worldCol++;
				if(worldCol == maxBlockCol) {
					worldCol = 0;
					worldRow++;
				}
			}
		}
	}
}
