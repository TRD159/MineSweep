package com.company;

public class MineGame {
    public static final int PLAYING = 0, WIN = 1, LOSE = 2, NOGAME = 3;

    MineBoard map;

    int ro, co, mi, ma, ds, st; //ro = row, co = column, mi = mines, ma = marked, st = state, ds = deadseconds
    long sTime;

    public MineGame(int ro, int co, int mi) {
        this.ro = ro;
        this.co = co;
        this.mi = mi;

        ma = 0;

        st = NOGAME;

        ds = 0;
        sTime = System.nanoTime();

        makeGame(3, 3);
    }

    public MineBoard getMap() {
        return map;
    }

    public int getRo() {
        return ro;
    }

    public int getCo() {
        return co;
    }

    public int getMi() {
        return mi;
    }

    public int getMa() {
        return ma;
    }

    public int getSt() {
        return st;
    }

    //Number of unmarked mines
    public int getMiC() {
        int MiC = 0;

        for(int x = 0; x < co; x++) {
            for(int y = 0; y < ro; y++) {
                if(map.grid[y][x].isMine() && map.grid[y][x].getState() == 1) {
                    MiC++;
                }
            }
        }

        return MiC;
    }

    public MineBoard getBoard() {
        return map;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public void reveal(int y, int x) {
        if ((y < 0 || y >= map.r) || (x < 0 || x >= map.c))
            return;
        if(map.getSpace(x, y).getState() == MineSpace.SHOWN)
            return;
        if(map.grid[y][x].getState() == MineSpace.FLAG) {
            return;
        }
        map.grid[y][x].setState(MineSpace.SHOWN);

        String t = map.getSpace(x, y).toString();
        if (t.equals("-")) {
            if(y > 0 && !map.grid[y - 1][x].isMine())
                reveal(y - 1, x);
            if(y < map.r - 1 && !map.grid[y + 1][x].isMine())
                reveal(y + 1, x);
            if(x > 0 && !map.grid[y][x - 1].isMine())
                reveal(y, x - 1);
            if(x < map.c - 1 && !map.grid[y][x + 1].isMine())
                reveal(y, x + 1);
        } else if(t.equals("M")) {
            for(int r = 0; r < map.grid.length; r++) {
                for(int c = 0; c < map.grid[0].length; c++) {
                    if(map.grid[r][c].isMine())
                        map.grid[r][c].setState(MineSpace.SHOWN);
                }
            }
            st = LOSE;
            ds = (int)(System.nanoTime()/1000000000);
        }

        int m = 0;
        int mr = 0;

        for(int r = 0; r < map.grid.length; r++) {
            for(int c = 0; c < map.grid[0].length; c++) {
                if(map.grid[r][c].isMine())
                    m++;
                if(map.grid[r][c].getState() == MineSpace.UP)
                    mr++;

            }
        }
        if(m == mr) {
            st = WIN;
            ds = (int)(System.nanoTime()/1000000000);
        }
    }

    public void makeGame(int r, int c) {
        map = new MineBoard(ro, co, mi, c, r);
        //System.out.println(map.toString());
    }

    public void check() {

    }
}
