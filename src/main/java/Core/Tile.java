package Core;

import java.awt.*;
import java.awt.image.*;

public class Tile{
    public Texture texture;
    public boolean canColision;
    public int worldX, worldY;
    public int screenX, screenY;
    public Tile(Texture texture, int worldX, int worldY, boolean canColision)
    {
        this.canColision = canColision;
        this.texture = texture;
        this.worldX = worldX;
        this.worldY = worldY;
    }
    public Rectangle getBounds()
    {
        return new Rectangle(worldX,worldY,texture.width,texture.height);
    }
}
