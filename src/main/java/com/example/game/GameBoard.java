package com.example.game;

public class GameBoard {
    public final int height;
    public final int width;

    private Tile[][] gameBoard;
    private byte[][] barriers;//1 means there is a barrier, 0 means there isn't

    /**
     * constructs a new gameboard. This gameboard consists of a 2D Tile Array where the fields are generated
     * pseudo-randomly. It is a 2D Array to have easier access.
     * every gameboard has an underlying 2D byte array to check whether there is a barrier on a field
     * Every gameboard also needs a player.
     * The visual representation happens in class "Draw".
     *
     * IMPORTANT: gameBoard and barriers is [height][width] so [y][x] and not [x][y]
     */
    public GameBoard(int height, int width){
        this.height = height;
        this.width = width;

        gameBoard = new Tile[height][width];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (j == 0 || j == width -1){
                    gameBoard[i][j] = new Tile(Tile.TileType.GRAY);
                }
                else gameBoard[i][j] = new Tile(Tile.TileType.getRandomTileType());
            }
        }

        barriers = new byte[height][width];//same as gameBoard
    }

    public void makeVisitedTileGray(int x, int y){
        gameBoard[y][x] = new Tile(Tile.TileType.GRAY);
    }

    public void executeEvent(Player player){
        gameBoard[player.getPosy()][player.getPosx()].type.tileEvent.execute(player, this);
    }

    public int[] getTileRGB(int x, int y){
        return gameBoard[y][x].type.rgb;
    }

    public void changeTile(int x, int y){
        gameBoard[y][x] = new Tile(Tile.TileType.getRandomTileType());
    }

    public boolean isBlockedByBarrier(int x, int y){
        if (barriers[y][x]==1) return true;
        return false;
    }

    public void removeBarrier(int x, int y){
        barriers[y][x] = 0;
    }

    public void addBarrier(int x, int y){
        barriers[y][x] = 1;
    }
}
