package com.company;

import javax.swing.*;
import java.awt.*;

public class MineFrame extends JFrame {
    MineBoard b;

    JMenuBar men = new JMenuBar();

    JMenu fil = new JMenu("File");
    JMenu hel = new JMenu("Help");

    JMenu nu = new JMenu("New Game");
    JMenuItem ez = new JMenuItem("Easy");
    JMenuItem med = new JMenuItem("Medium");
    JMenuItem har = new JMenuItem("Hard");

    JMenuItem hi = new JMenuItem("High Scores");

    JMenuItem ex = new JMenuItem("Exit");

    JMenuItem rul = new JMenuItem("Rules");

    JMenuItem abt = new JMenuItem("About");

    public MineFrame(String title) throws HeadlessException {
        super(title);

        setSize(250, 250);
        setResizable(false);

        setDefaultCloseOperation(3);

        ez.addListener

        nu.add(ez);
        nu.add(med);
        nu.add(har);

        fil.add(nu);
        fil.add(hi);
        fil.add(ex);

        hel.add(rul);
        hel.add(abt);

        men.add(fil);
        men.add(hel);

        setLayout(null);

        setJMenuBar(men);

        setVisible(true);
    }

    public void easy() {
        b = new MineBoard(0);
    }

    public void medium() {
        b = new MineBoard(1);
    }

    public void hard() {
        b = new MineBoard(2);
    }
}
