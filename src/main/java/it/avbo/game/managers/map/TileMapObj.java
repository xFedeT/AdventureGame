package it.avbo.game.managers.map;

import it.avbo.game.graphics.Sprite;
import it.avbo.game.managers.map.blocks.Block;
import it.avbo.game.managers.map.blocks.HoleBlock;
import it.avbo.game.managers.map.blocks.ObjBlock;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;
import java.util.HashMap;

public class TileMapObj extends TileMap {
    public static HashMap<String, Block> tmo_blocks;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tmo_blocks = new HashMap<>();

        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                if (temp == 172) {
                    tempBlock = new HoleBlock(sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                } else {
                    tempBlock = new ObjBlock(sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
                }
                tmo_blocks.put((int) (i % width) + "," + (int) (i / height), tempBlock);
            }
        }
    }

    public void render(Graphics2D graphics2D) {
        for (Block block : tmo_blocks.values()) {
            block.render(graphics2D);
        }
    }

}
