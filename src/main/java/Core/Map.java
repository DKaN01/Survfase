package Core;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.json.simple.*;

public class Map {
    HashMap<Integer, Sprite> types;
    ArrayList<Tile> tiles;
	Player player;
	Core core;
	
	final int maxBlockRow = 50;
	final int maxBlockCol = 50;
	int blockSizeDefault = 16;
	
	public Map(TextureManager tm, Core core) {
		this.core = core;
        types = new HashMap<>();
        tiles = new ArrayList<>();


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
                boolean canColusion = num == 1;
                tiles.add(new Tile(types.get(num).texture, blockSizeDefault * worldCol, blockSizeDefault * worldRow, canColusion));
                worldCol++;
            }
			if(worldCol == maxBlockCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
    public void addTilesColusion()
    {
        for(Tile tile: tiles)if(tile.canColision)core.em.player.addTileColusion(tile);
    }
	public void update() {
		blockSizeDefault = core.kh.Mashab;
	}
	public void draw(Graphics2D g2) {
		if(player != null) {

            for(Tile tile: tiles)
            {
                int worldX = tile.worldX;
                int worldY = tile.worldY;
                int screenX = worldX - (int)player.worldX + player.screenX;
                int screenY = worldY - (int)player.worldY + player.screenY;

                g2.drawImage(tile.texture.getTexture(), screenX, screenY,blockSizeDefault,blockSizeDefault,null);
            }
		}
	}
}
