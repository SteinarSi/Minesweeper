package Minesweeper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<T> implements Iterable<T>{
    private List<T> cells;
    private int height;
    private int width;

    /**
     *
     * Construct a grid with the given dimensions.
     *
     * @param width
     * @param height
     * @param initElement
     *            What the cells should initially hold (possibly null)
     */
    public Grid(int width, int height, T initElement) {
        if(width <= 0 || height <= 0)
            throw new IllegalArgumentException();

        this.height = height;
        this.width = width;
        cells = new ArrayList<>(height * width);
        for (int i = 0; i < height * width; ++i) {
            cells.add(initElement);
        }
    }
    public boolean contains(Tuple<Integer, Integer> xy) { return contains(xy.getX(), xy.getY()); }

    public boolean contains(int x, int y){ return (x>=0 && y>=0 && x<width && y<height); }

    public Tuple<Integer, Integer> getCoords(T elem){
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                if(get(x, y) == elem) return new Tuple(x, y);
            }
        }
        throw new IllegalArgumentException("Couldn't find the object");
    }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public void set(Tuple<Integer, Integer> xy, T elem){ set(xy.getX(), xy.getY(), elem);}

    public void set(int x, int y, T elem) {
        if(!contains(x, y)) throw new IndexOutOfBoundsException();
        cells.set(coordinateToIndex(x, y), elem);
    }

    private int coordinateToIndex(int x, int y) {
        return x + y*width;
    }

    public T get(Tuple<Integer, Integer> xy){ return get(xy.getX(), xy.getY()); }

    public T get(int x, int y) {
        if(x < 0 || x >= width)
            throw new IndexOutOfBoundsException("Index out of bounds. x: " + x + " width: " + width);
        if(y < 0 || y >= height)
            throw new IndexOutOfBoundsException("Index out of bounds. y: " + x + " height: " + height);

        return cells.get(coordinateToIndex(x, y));
    }

    public Grid<T> copy() {
        Grid<T> newGrid = new Grid<>(getWidth(), getHeight(), null);
        for (int x = 0; x < width; x++) for(int y = 0; y < height; y++) newGrid.set(x,  y,  get(x, y));
        return newGrid;
    }

    public Iterator<T> iterator() {
        return cells.iterator();
    }

}