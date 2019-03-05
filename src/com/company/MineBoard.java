package com.company;

public class MineBoard {
    boolean[][] board;

    public static final int S = 0, M = 1, L = 2;

    public MineBoard(int t) {
        switch (t) {
            case S:
                board = new boolean[5][5];
                for(int x = 0; x < 5; x++) {
                    for(int y = 0; y < 5; y++) {
                        board[x][y] = ((int)(Math.random() * 20) < 5);
                    }
                }
                break;
            case M:
                board = new boolean[10][10];
                for(int x = 0; x < 10; x++) {
                    for(int y = 0; y < 10; y++) {
                        board[x][y] = ((int)(Math.random() * 20) < 10);
                    }
                }
                break;
            case L:
                board = new boolean[15][15];
                for(int x = 0; x < 15; x++) {
                    for(int y = 0; y < 15; y++) {
                        board[x][y] = ((int)(Math.random() * 20) < 15);
                    }
                }
                break;
        }

    }
}
