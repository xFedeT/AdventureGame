package it.avbo.game.managers;

import it.avbo.game.graphics.Sprite;
import it.avbo.game.managers.map.TileMap;
import it.avbo.game.managers.map.TileMapNorm;
import it.avbo.game.managers.map.TileMapObj;
import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Getter
public class TileManager {

    public static ArrayList<TileMap> tm;

    public TileManager() {
        tm = new ArrayList<TileMap>();
    }

    public TileManager(String path) {
        tm = new ArrayList<TileMap>();
        addTileMap(path, 64, 64);
    }

    public void addTileMap(String path, int blockWidth, int blockHeigth) {
        String imagePath;

        int width = 0;
        int heigth = 0;
        int tileWidth;
        int tileHeigth;
        int tileCount;
        int tileColumns;
        int layers;
        Sprite sprite;

        String[] data = new String[10];

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));

            NodeList list = document.getElementsByTagName("map");
            Node node = list.item(0);
            Element element = (Element) node;

            imagePath = element.getAttribute("name");
            tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
            tileHeigth = Integer.parseInt(element.getAttribute("tileheight"));
            tileColumns = Integer.parseInt(element.getAttribute("columns"));

            sprite = new Sprite("maps/" + imagePath + ".png", tileWidth, tileHeigth);

            list = document.getElementsByTagName("layer");
            layers = list.getLength();

            for (int i = 0; i < layers; i++) {
                node = list.item(i);
                element = (Element) node;

                if (i <= 0) {
                    width = Integer.parseInt(element.getAttribute("width"));
                    heigth = Integer.parseInt(element.getAttribute("height"));
                }

                data[i] = element.getElementsByTagName("data").item(0).getTextContent();

                if (i >= 1) {
                    tm.add(new TileMapNorm(data[i], sprite, width, heigth, blockWidth, blockHeigth, tileColumns));
                } else {
                    tm.add(new TileMapObj(data[i], sprite, width, heigth, blockWidth, blockHeigth, tileColumns));
                }

            }

        } catch (ParserConfigurationException | URISyntaxException | SAXException | IOException e) {
            System.out.println("ERROR - TILEMANAGER: cannot load TileMap");
        }

    }

    public void render(Graphics2D graphics2D) {
        for (TileMap tileMap : tm) {
            tileMap.render(graphics2D);
        }
    }

}
