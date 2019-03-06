package com.company;

public class MineGame {
    public static final int PLAYING = 0, WIN = 1, LOSE = 2, NOGAME = 3;

    MineBoard map;

    int ro, co, mi, ma, ds, st;
    long sTime;

    public MineGame(int ro, int co, int mi) {
        this.ro = ro;
        this.co = co;
        this.mi = mi;

        ma = 0;

        st = NOGAME;

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
}
