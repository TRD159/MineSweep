package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MineFrame extends JFrame implements Runnable {
    JMenuBar men;

    JMenu fil;
    JMenu hel;

    JMenu nu;

    JMenuItem sez;
    JMenuItem ez;
    JMenuItem nm;
    JMenuItem hd;
    JMenuItem tst;

    JMenuItem hi;

    JMenuItem ex;

    JMenuItem rul;

    JMenuItem abt;

    MinePanel p;

    public MineFrame(int nRow, int nCol, int nMin) throws HeadlessException {
        super();

        setDefaultCloseOperation(3);
        setLayout(null);

        //setPreferredSize(new Dimension(nCol * 16, nRow * 16));
        pack();

        setMinimumSize(new Dimension(20, 20));
        setMaximumSize(new Dimension(400, 400));

        p = new MinePanel(nRow, nCol, nMin);
        add(p);

        sez = new JMenuItem("Super Easy");
        ez = new JMenuItem("Easy");
        nm = new JMenuItem("Normal");
        hd = new JMenuItem("Hard");
        tst = new JMenuItem("Test");

        sez.addActionListener(e -> superEasy());
        ez.addActionListener(e -> easy());
        nm.addActionListener(e -> medium());
        hd.addActionListener(e -> hard());
        tst.addActionListener(e -> test());

        nu = new JMenu("New Game") {
            {
                add(sez);
                add(ez);
                add(nm);
                add(hd);
                add(tst);
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

        setResizable(true);

        //setPreferredSize(new Dimension(nCol * 16, nRow * 16));
        pack();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);

        //System.out.println(getHeight() - getInsets().bottom - getInsets().top);
        //System.out.println(p.getHeight());

        setVisible(true);
    }

    public void superEasy() {
        //System.out.println(getContentPane());
        getContentPane().remove(p);
        p = new MinePanel(10, 10, 5);
        add(p);
        //System.out.println(getContentPane());

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);
    }

    public void easy() {
        //System.out.println(getContentPane());
        getContentPane().remove(p);
        p = new MinePanel(10, 10, 15);
        add(p);
        //System.out.println(getContentPane());

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);
    }

    public void medium() {
        getContentPane().remove(p);
        p = new MinePanel(15, 15, 40);
        add(p);

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);
    }

    public void hard() {
        getContentPane().remove(p);
        p = new MinePanel(20, 20, 100);
        add(p);

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);
    }

    public void test() {
        getContentPane().remove(p);
        p = new MinePanel(10, 10, 97);
        add(p);

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);
    }

    @Override
    public void run() {

    }
}
