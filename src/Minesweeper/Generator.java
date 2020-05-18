package Minesweeper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Generator {
    public static Hashtable<Character, Image> makeImageDict(){
        Hashtable<Character, Image> ret = new Hashtable<>();
        for(char name : new char[]{
            'F',
            'M'
        }){
            try{
                File fil = new File("src\\Images\\"+name+".png");
                Image image = ImageIO.read(fil).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                ret.put(name, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
