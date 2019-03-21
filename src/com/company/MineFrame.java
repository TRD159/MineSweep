package com.company;

import javax.swing.*;
import java.awt.*;

public class MineFrame extends JFrame {


    public MineFrame(int nRow, int nCol, int nMin) throws HeadlessException {
        super();

        setDefaultCloseOperation(3);
        setLayout(null);

        setPreferredSize(new Dimension(nCol * 16, nRow * 16));
        pack();

        MinePanel p = new MinePanel(nRow, nCol, nMin);
        add(p);



        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom);

        setVisible(true);
    }
}
