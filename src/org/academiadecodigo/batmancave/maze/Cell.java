package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Cell {

    private CellType type;
    private Position pos;
    private boolean excavated;
    private boolean path;
    private boolean visited;
    private Rectangle cellGfx;


    public Cell(CellType type, int col, int row) {
        this.type = type;
        pos = new Position(col, row);
        excavated = false;
        path = false;
        visited = false;
    }


    public Position getPosition() {
        return pos;
    }

    public CellType getType() {
        return type;
    }

    public boolean isExcavated() {
        return excavated;
    }



    public void setType(CellType type) {
        this.type = type;
    }

    public void setCellGfx(Rectangle cellGfx) {
        this.cellGfx = cellGfx;
    }

    public Rectangle getCellGfx() {
        return cellGfx;
    }

    public void excavate() {
        excavated = true;
    }

    public boolean isPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
