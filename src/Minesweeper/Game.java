package Minesweeper;

import java.util.ArrayList;

import static Minesweeper.Main.HEIGHT;
import static Minesweeper.Main.WIDTH;

public class Game {

    Grid<Tile> grid;

    public Game(){
        grid = new Grid(WIDTH, HEIGHT, null);
        for(int y=0; y<grid.getHeight(); y++){
            for(int x=0; x<grid.getWidth(); x++){
                grid.set(x, y, new Tile(x, y));
            }
        }
        for(int y=0; y<grid.getHeight(); y++){
            for(int x=0; x<grid.getWidth(); x++){
                ArrayList<Tile> neighs = getNeighbourhood(x, y);
                int i=0;
                for(Tile t: neighs) if(t.isMine()) i++;
                grid.get(x, y).setNeighbours(i);
            }
        }
    }

    public int getHeight(){ return grid.getHeight(); }
    public int getWidth(){ return grid.getWidth(); }

    public Tile getTile(int x, int y){ return grid.get(x, y); }

    public ArrayList<Tile> getNeighbourhood(int X, int Y){
        ArrayList ret = new ArrayList();
        for(int y=Y-1; y<=Y+1; y++) for(int x=X-1; x<=X+1; x++) if(grid.contains(x, y)) ret.add(grid.get(x, y));
        return ret;
    }
    public void mark(int x, int y){ grid.get(x, y).markUnMark(); }

    public boolean checkWinCondition(){
        for(Tile t : grid){
            if(t.isMarked() && !t.isMine()) return false;
            if(!t.isMarked() && t.isMine()) return false;
        }
        return true;
    }
}
