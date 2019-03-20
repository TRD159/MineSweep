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
        map.grid[y][x].setState(MineSpace.SHOWN);
        if(map.grid[y][x].toString().equals("-")) {
            reveal(y - 1, x);
            reveal(y + 1, x);
            reveal(y, x - 1);
            reveal(y, x + 1);
        }
    }

    public void makeGame(int r, int c) {
        map = new MineBoard(ro, co, mi, c, r);
        System.out.println(map.toString());
    }

    public void check() {

    }
}
