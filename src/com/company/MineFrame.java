package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

        rul.addActionListener(e -> help());

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
                try {
                    FileInputStream ea = new FileInputStream("Easy.txt");
                    FileInputStream me = new FileInputStream("Medium.txt");
                    FileInputStream hd = new FileInputStream("Hard.txt");

                    ObjectInputStream Ea = new ObjectInputStream(ea);

                    p.setScorese((ArrayList<Score>)Ea.readObject());

                    ea.close();
                    Ea.close();

                    ObjectInputStream Me = new ObjectInputStream(me);

                    p.setScoresn((ArrayList<Score>)Me.readObject());

                    Me.close();
                    me.close();

                    ObjectInputStream Hd = new ObjectInputStream(hd);

                    p.setScoresh((ArrayList<Score>)Hd.readObject());

                    Hd.close();
                    hd.close();
                } catch (Exception x) {

                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    BufferedWriter es = new BufferedWriter(new FileWriter("Easy.txt"));
                    es.write("");
                    int x = 1;
                    for(Score s: p.scorese) {
                        es.append(x + s.toString());
                        es.newLine();
                        x++;
                    }
                } catch (Exception x) {

                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);

        //System.out.println(getHeight() - getInsets().bottom - getInsets().top);
        //System.out.println(p.getHeight());

        Thread t = new Thread(this);
        t.start();

        setVisible(true);
    }

    public void help() {
        JTextArea t = new JTextArea("Your goal is to reveal every \n" +
                "space that isn't a mine.\n\n" +
                "Left click reveals a space, and \n" +
                "if it contains a mine, you lose the game.\n\n" +
                "Revealing a space also reveals all\n" +
                "non-mine spaces around it.\n\n" +
                "Revealed spaces can have a number\n" +
                "which repesents the number\n" +
                "of mines around it.\n\n" +
                "You can right click a space\n" +
                "to mark it with a flag or a question.\n\n" +
                "If a space has marked spaces around it\n" +
                "equal to its number, you can middle\n" +
                "click it to reveal all spaces around it.\n" +
                "(Note: This does not work yet)\n\n");
        JFrame txt = new JFrame("Rules");

        txt.add(t);

        txt.pack();

        txt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txt.setVisible(true);
    }

    public void about() {
        JTextArea t = new JTextArea("Minesweeper version 4.20\nDeveloped by Rohan Kancherla");
        JFrame txt = new JFrame("Rules");

        txt.add(t);

        txt.pack();

        txt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txt.setVisible(true);
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
        //System.out.println(p.game.map.toString());
    }

    public void test() {
        getContentPane().remove(p);
        p = new MinePanel(10, 10, 97);
        add(p);

        setSize(p.getWidth() + getInsets().left + getInsets().right, p.getHeight() + getInsets().top + getInsets().bottom + 23);

        //System.out.println(p.game.map.toString());
    }

    @Override
    public void run() {
        long wait = (long)(1000.0/30), startTime = System.nanoTime();
        int uDone = 0;
        while(true) {
            long uNeed = ((System.nanoTime() - startTime)/1000)/wait;
            boolean sR = false;
            for(; uDone < uNeed; uDone++) {
                p.update();
                sR = true;
            }
            if(sR) {
                repaint();
            }
        }
    }
}
