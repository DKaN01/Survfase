package Core;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Map {
    HashMap<Integer, Sprite> types;
	Sprite[][] tiles;
	Player player;
	Core core;
	
	final int maxBlockRow = 50;
	final int maxBlockCol = 50;
	int blockSizeDefault = 16;
	
	public Map(TextureManager tm, Core core) {
		this.core = core;
        types = new HashMap<>();
        tiles = new Sprite[maxBlockCol][maxBlockRow];


        types.put(0,new Sprite(tm.floor_1));
        types.put(1,new Sprite(tm.wallMid));

		core.kh.Mashab = blockSizeDefault;
		init();
	}


	public void setPlayer(Player player) {
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
				tiles[worldCol][worldRow] = types.get(num);
                worldCol++;
            }
			if(worldCol == maxBlockCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
	public void update() {
		blockSizeDefault = core.kh.Mashab;
	}
	public void draw(Graphics2D g2) {
		if(player != null) {

            int worldCol = 0;
            int worldRow = 0;

			while(worldCol < maxBlockCol && worldRow < maxBlockRow) {
				
				int worldX = worldCol * blockSizeDefault;
				int worldY = worldRow * blockSizeDefault;
				int screenX = worldX - (int)player.worldX + player.screenX;
				int screenY = worldY - (int)player.worldY + player.screenY;

				g2.drawImage(tiles[worldCol][worldRow].texture.getTexture(), screenX, screenY,blockSizeDefault,blockSizeDefault,null);
				
				worldCol++;
				if(worldCol == maxBlockCol) {
					worldCol = 0;
					worldRow++;
				}
			}
		}
	}
}
