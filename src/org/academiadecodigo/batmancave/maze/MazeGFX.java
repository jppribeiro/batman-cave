package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MazeGFX {

    public static final int PADDING = 10;
    public static final int CELLSIZE = 30;

    public void placePictures(Cell[][] layout) {

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {

                CellType cell = layout[i][j].getType();

                Picture cellTexture;

                switch (cell) {
                    case WALL:
                        if(j == layout[0].length - 1) {
                            // Bottom line
                            cellTexture = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/bottom_wall.png");
                        } else if (layout[i][j+1].getType() == CellType.ROOM) {
                            // Wall with room below
                            cellTexture = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/bottom_wall.png");
                        } else {
                            // regular wall
                            cellTexture = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/wall.png");
                        }
                        break;
                    case ROOM:
                        /*if(i   == flagStart.getCol() && j   == flagStart.getRow()) {
                            cellTexture = new Picture(i * CELLSIZE+PADDING, j * CELLSIZE+PADDING, "resources/Wall/power_crystal.png");
                        } else { */
                            cellTexture = new Picture(i * CELLSIZE + PADDING, j * CELLSIZE + PADDING, "resources/Wall/room.png");
                        //}
                        break;
                    default:
                        cellTexture = null;
                        break;
                }

                layout[i][j].setCellGfx(cellTexture);
            }
        }
    }

    public void init(int cols, int rows) {
        Rectangle window = new Rectangle(PADDING, PADDING, cols * CELLSIZE, rows * CELLSIZE);
        window.setColor(Color.BLACK);
        window.fill();
    }

    public void draw(Cell[][] layout, Player[] players) {

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {

                if (players[0].checkVisibility(layout[i][j].getPosition()) ||
                    players[1].checkVisibility(layout[i][j].getPosition())) {

                    try {
                        layout[i][j].getCellGfx().draw();
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("Error: " + i + ", " + j);
                    }


                } else {

                    layout[i][j].getCellGfx().delete();

                }
            }
        }
    }
}
