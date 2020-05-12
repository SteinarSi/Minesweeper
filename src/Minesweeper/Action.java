package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.jar.JarOutputStream;

import static Minesweeper.Main.game;
import static Minesweeper.Main.gui;
import static Minesweeper.Gui.*;

public class Action implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Component butt = mouseEvent.getComponent();
        if(butt == quit) System.exit(1);
        else if(butt == neww) gui.newGame();

        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                if (gui.getButton(x, y) == mouseEvent.getComponent()) {
                    if (SwingUtilities.isRightMouseButton(mouseEvent)) gui.rightClick(x, y);
                    else if (SwingUtilities.isLeftMouseButton(mouseEvent)) gui.leftClick(x, y);
                    if(game.checkWinCondition()) gui.win();
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
