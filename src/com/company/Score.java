package com.company;

import java.io.Serializable;

public class Score implements Comparable, Serializable {
    int s; String nam;

    public Score(int s, String nam) {
        this.s = s;
        this.nam = nam;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Score) {
            return s - ((Score) o).s;
        }
        return 0;
    }

    public int getS() {
        return s;
    }

    public String getNam() {
        return nam;
    }

    public String toString() {
        return nam + "-" + s;
    }
}
