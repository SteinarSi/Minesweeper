package Minesweeper;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Minesweeper.Main.game;
import static Minesweeper.Main.imageDict;
import static java.awt.Color.*;

public class Gui extends JPanel {

    private JFrame frame = new JFrame("Minesweeper");
    private Grid<JButton> buttons = new Grid(game.getWidth(), game.getHeight(), null);
    public static JButton quit = new JButton("Quit Game");
    public static JButton neww = new JButton("New Game");

    public Gui(){
        frame.add("Center", this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(initialize());
        frame.setMinimumSize(getSize());
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel initialize(){
        JPanel ret = new JPanel();
        ret.add(createToolbar(), TOP_ALIGNMENT);
        ret.add(createButtonGrid(), BOTTOM_ALIGNMENT);
        return ret;
    }

    private JToolBar createToolbar() {
        JToolBar ret = new JToolBar();
        ret.add(quit);
        ret.add(neww);
        quit.addMouseListener(new Action());
        neww.addMouseListener(new Action());
        return ret;
    }

    private JPanel createButtonGrid(){
        JPanel ret = new JPanel(new GridLayout(game.getHeight(), game.getWidth()));
        for(int y=0; y<game.getHeight(); y++){
            for(int x=0; x<game.getWidth(); x++){
                JButton butt = new JButton();
                butt.addMouseListener(new Action());
                butt.setPreferredSize(getDimension());
                butt.setBackground(GRAY);
                ret.add(butt);
                buttons.set(x, y, butt);
            }
        }
        return ret;
    }

    private Dimension getDimension(){ return new Dimension(50, 50); }

    public JButton getButton(int x, int y){ return buttons.get(x, y); }

    private void revealNeighbours(int x, int y){

        int b = 0;
        for(Tile n : game.getNeighbourhood(x, y)){
            if(n.isMarked()) b++;
        }
        if(b != game.getTile(x, y).getNeighbours()){
            JButton butt = buttons.get(x, y);
            butt.setBackground(WHITE);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {}
            butt.setBackground(LIGHT_GRAY);
            return;
        }

        for(Tile neigh : game.getNeighbourhood(x, y)){
            int X = neigh.getX();
            int Y = neigh.getY();
            if(neigh.isRevealed() || neigh.isMarked()) continue;
            else if(neigh.isMine()){
                gameOver(X, Y);
                break;
            }
            else{
                neigh.reveal();
                JButton butt = buttons.get(X, Y);
                int n = neigh.getNeighbours();
                if(n == 0){
                    revealNeighbours(X, Y);
                }
                else{
                    butt.setText("" + n);
                }
                butt.setBackground(LIGHT_GRAY);
            }
        }
    }

    private void gameOver(int x, int y) {
        //ImageIcon icon = new ImageIcon();
        //icon.setImage(imageDict.get('M'));
        //buttons.get(x, y).setIcon(icon);
        buttons.get(x, y).setBackground(RED);
        displayMessage("You lose!");
        newGame();
    }

    public void leftClick(int x, int y){
        Tile tile = game.getTile(x, y);
        if(tile.isMarked()) return;
        else if(tile.isMine()) gameOver(x, y);
        else if(tile.isRevealed()) revealNeighbours(x, y);
        else {
            tile.reveal();
            JButton butt = buttons.get(x, y);
            int n = tile.getNeighbours();
            if (n == 0) {
                revealNeighbours(x, y);
                butt.setBackground(LIGHT_GRAY);
            } else {
                butt.setBackground(LIGHT_GRAY);
                butt.setText("" + n);
            }
        }
    }

    public void rightClick(int x, int y) {
        Tile tile = game.getTile(x, y);
        if(!tile.isRevealed()) {
            game.mark(x, y);
            if(tile.isMarked()){
                ImageIcon icon = new ImageIcon();
                icon.setImage(imageDict.get('F'));
                buttons.get(x, y).setIcon(icon);

            }
            else buttons.get(x, y).setIcon(new ImageIcon());
        }
    }

    public void newGame() {
        game = new Game();
        for(JButton butt : buttons){
            butt.setBackground(GRAY);
            butt.setText("");
            butt.setIcon(new ImageIcon());
        }
    }
    private void displayMessage(String s) { JOptionPane.showMessageDialog(this, s); }

    public void win() {
        displayMessage("You won!");
    }
}
