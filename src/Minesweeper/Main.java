package Minesweeper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class Main {

    public static int HEIGHT = 15;
    public static int WIDTH = 30;
    public static double MINE_DENSITY = 0.2;
    public static Game game = new Game();
    public static Gui gui = new Gui();
    public static Hashtable<Character, Image> imageDict = Generator.makeImageDict();

    public static void main(String[] args) {}

}
