package com.company;

public class MineBoard {
   MineSpace[][] grid;

   int r, c, m;

    public MineBoard(int r, int c, int m, int cc, int cr) {
        this.r = r;
        this.c = c;
        this.m = m;

        createMap(cc, cr);
    }

    public void createMap(int cc, int cr) {
        grid = new MineSpace[r][c];
        for(int y = 0; y < grid.length; y++) {
            for(int x = 0; x < grid[0].length; x++) {
                grid[y][x] = new MineSpace();
            }
        }

        int t = r * c;

        int mi = 0;

        while(true) {
            if(mi >= m)
                break;

            //System.out.println(mi);

            int cm = (int)(Math.random() * c);
            int rm = (int)(Math.random() * r);

            if(grid[rm][cm].isMine() || (cm == cc && rm == cr)) {
                continue;
            }

            grid[rm][cm].setMine(true);

            if(++mi >= m) {
                break;
            }
        }

        for(int y = 0; y < r; y++) {
            for(int x = 0; x < c; x++) {
                for(int yi = -1; yi <= 1; yi++) {
                    for(int xi = -1; xi <= 1; xi++) {
                        if((xi == 0 && yi == 0) || (getSpace(x + xi, y + yi) == null))
                            continue;
                        if(grid[y + yi][x + xi].isMine())
                            grid[y][x].setNum(grid[y][x].getNum() + 1);
                    }
                }
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(MineSpace[] r: grid) {
            for(MineSpace c: r) {
                s.append(c.toString());
            }
            s.append("\n");
        }
        return s.toString();
    }
}
