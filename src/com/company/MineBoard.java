package com.company;

public class MineBoard {
   MineSpace[][] grid;

   int r, c, m;

    public MineBoard(int r, int c, int m, int cc, int cr) {
        this.r = r;
        this.c = c;
        this.m = m;
    }

    public void createMap(int cc, int cr) {
        grid = new MineSpace[r][c];
        int t = r * c;

        for(int y = 0; y < r; y++) {
            for(int x = 0; x < c; x++) {
                grid[y][x] = new MineSpace();
                if(y == cr && x == cc)
                    continue;
                grid[y][x].setMine((int)(Math.random() * t) < m);
            }
        }
    }

    public MineSpace getSpace(int c, int r) {
        try {
            return grid[r][c];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
