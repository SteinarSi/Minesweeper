package Minesweeper;

import java.util.Random;

import static Minesweeper.Main.MINE_DENSITY;

public class Tile {

    private boolean isMine;
    private int x;
    private int y;
    private int neighbours;
    private boolean isRevealed = false;
    private boolean isMarked = false;

    public Tile(boolean isMine, int x, int y){
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        Random ran = new Random();
        if(ran.nextDouble() <= MINE_DENSITY) isMine = true;
    }

    public boolean isMine(){ return isMine; }
    public boolean isRevealed(){ return isRevealed; }

    public int getX(){ return x; }
    public int getY(){ return y; }

    public void setNeighbours(int i) { neighbours = i; }
    public int getNeighbours(){ return neighbours; }

    public void reveal(){ isRevealed = true; }

    public void markUnMark(){ isMarked = !isMarked; }
    public boolean isMarked() { return isMarked; }
}
