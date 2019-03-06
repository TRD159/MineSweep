package com.company;

public class MineSpace {
    static final int SHOWN = 0, UP = 1, FLAG = 2, QUESTION = 3;

    boolean mine;
    int num, state;

    public MineSpace() {
        mine = false;
        num = 0;
        state = UP;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        if(mine)
            return "M";
        else if (num > 0)
            return ""+num;
        else
            return "-";
    }
}
