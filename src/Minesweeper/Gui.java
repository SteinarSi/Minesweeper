package Minesweeper;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static Minesweeper.Main.game;
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
                butt.setPreferredSize(new Dimension(50, 50));
                butt.setBackground(GRAY);
                ret.add(butt);
                buttons.set(x, y, butt);
            }
        }
        return ret;
    }

    public JButton getButton(int x, int y){ return buttons.get(x, y); }

    private void revealNeighbours(int x, int y){
        for(Tile neigh : game.getNeighbourhood(x, y)){
            int X = neigh.getX();
            int Y = neigh.getY();
            if(neigh.isRevealed() || neigh.isMarked()) continue;
            else if(neigh.isMine()) gameOver(X, Y);
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
            if(tile.isMarked()) buttons.get(x, y).setBackground(GREEN);
            else buttons.get(x, y).setBackground(GRAY);
        }
    }

    public void newGame() {
        game = new Game();
        for(JButton butt : buttons){
            butt.setBackground(GRAY);
            butt.setText("");
        }
    }
    private void displayMessage(String s) { JOptionPane.showMessageDialog(this, s); }

    public void win() {
        displayMessage("You won!");
    }
}
