package it.avbo.game.managers.map;

import it.avbo.game.graphics.Sprite;
import it.avbo.game.managers.map.blocks.Block;
import it.avbo.game.managers.map.blocks.NormBlock;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;

    public TileMapNorm(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        blocks = new ArrayList<>();

        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                blocks.add(new NormBlock(sprite.getSprite((temp - 1) % tileColumns, (temp - 1) / tileColumns), new Vector2f((int) (i % width) * tileWidth, (i / height) * tileHeight), tileWidth, tileHeight));
            }
        }
    }


    @Override
    public void render(Graphics2D graphics2D) {

    }
}
