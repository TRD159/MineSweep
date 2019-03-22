package com.company;

import javax.swing.*;
import java.awt.*;

public class MineFrame extends JFrame {
    JMenuBar men;

    JMenu fil;
    JMenu hel;

    JMenu nu;

    JMenuItem ez;
    JMenuItem nm;
    JMenuItem hd;

    JMenuItem hi;

    JMenuItem ex;

    JMenuItem rul;

    JMenuItem abt;

    MinePanel p;

    public MineFrame(int nRow, int nCol, int nMin) throws HeadlessException {
        super();

        setDefaultCloseOperation(3);
        setLayout(null);

        setPreferredSize(new Dimension(nCol * 16, nRow * 16));
        pack();

        MinePanel p = new MinePanel(nRow, nCol, nMin);
        add(p);

        ez = new JMenuItem("Easy");
        nm = new JMenuItem("Normal");
        hd = new JMenuItem("Hard");

        ez.addActionListener(e -> easy());

        nu = new JMenu("New Game") {
            {
                add(ez);
                add(nm);
                add(hd);
            }
        };

        hi = new JMenuItem("High Scores");

        ex = new JMenuItem("Exit");

        fil = new JMenu("File") {
            {
                add(nu);
                add(hi);
                add(ex);
            }
        };

        abt = new JMenuItem("About");

        rul = new JMenuItem("Rules");

        hel = new JMenu("Help") {
            {
                add(rul);
                add(abt);
            }
        };

        men = new JMenuBar() {
            {
                add(fil);
                add(hel);
            }
        };

        setJMenuBar(men);

        setResizable(false);

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom);

        setVisible(true);
    }

    public void easy() {
        p = new MinePanel(5, 5, 12);
        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom);
        add(p);
    }
}
