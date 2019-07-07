package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Stack;

public class MazeGFX {

    public static final int PADDING = 10;
    public static final int CELLSIZE = 14;

    public void placePictures(Cell[][] layout) {

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {

                CellType cell = layout[i][j].getType();

                //Picture cellTexture;
                Rectangle cellShape;

                Color wallColor = new Color(30, 20, 43);
                //Color wallColor = Color.CYAN;
                Color room = new Color(255, 251, 205);

                switch (cell) {
                    case WALL:
                        /*
                        if(j == layout[0].length - 1) {
                            // Bottom line
                            cellShape = new Rectangle(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, i * CELLSIZE, j * CELLSIZE);
                            cellShape.setColor(wall);
                            //cellShape = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/bottom_wall.png");
                        } else if (layout[i][j+1].getType() == CellType.ROOM) {
                            // Wall with room below
                            cellShape = new Rectangle(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, i * CELLSIZE, j * CELLSIZE);
                            cellShape.setColor(wall);
                            //cellShape = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/bottom_wall.png");
                        } else {*/
                            // regular wall
                            cellShape = new Rectangle(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING,  CELLSIZE, CELLSIZE);
                            cellShape.setColor(wallColor);
                            //cellShape = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/wall.png");
                        /*}*/
                        break;
                    case ROOM:
                        /*if(i   == flagStart.getCol() && j   == flagStart.getRow()) {
                            cellTexture = new Picture(i * CELLSIZE+PADDING, j * CELLSIZE+PADDING, "resources/Wall/power_crystal.png");
                        } else { */
                            //cellShape = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/room.png");
                        cellShape = new Rectangle( i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, CELLSIZE, CELLSIZE);
                        cellShape.setColor(room);
                        //}
                        break;
                    default:
                        cellShape = null;
                        break;
                }

                layout[i][j].setCellGfx(cellShape);
            }
        }
    }

    public void init(int cols, int rows) {
        //Rectangle window = new Rectangle(0, 0, cols * CELLSIZE, rows * CELLSIZE);
        //window.setColor(Color.BLACK);
        //window.fill();
    }

    public void draw(Cell[][] layout) {

        Color exit = new Color(192, 137, 235);

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {

                if((i == 0 && j == 1) || (i == layout.length-1 && j == layout[0].length -2) ) {
                    layout[i][j].getCellGfx().setColor(exit);
                }

                if (layout[i][j].isPath()) {
                    layout[i][j].getCellGfx().setColor(exit);
                }

                layout[i][j].getCellGfx().fill();

            }
        }
    }
}
