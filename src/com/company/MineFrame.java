package com.company;

import javax.swing.*;
import java.awt.*;

public class MineFrame extends JFrame {
    public MineFrame(int nRow, int nCol, int nMin) throws HeadlessException {
        super();

        setDefaultCloseOperation(3);

        setPreferredSize(new Dimension(nCol * 16 + 96, nRow * 16 + 96));
        pack();

        MinePanel p = new MinePanel(nRow, nCol, nMin);
        add(p);

        setSize(getWidth() + getInsets().left + getInsets().right, getHeight() + getInsets().top + getInsets().bottom);

        setVisible(true);
    }
}
