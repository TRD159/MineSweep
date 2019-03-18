package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class MinePanel extends JPanel implements Runnable {
    int numCol, numRow, numMin;
    double upPer = 1000.0/35.0;

    BufferedImage Dead, Oh, Happy, Happy_Down, Shades,
                    Digit_Nine, Digit_Eight, Digit_Seven,
                    Digit_Six, Digit_Five, Digit_Four,
                    Digit_Three, Digit_Two, Digit_One,
                    Digit_Zero, Eight, Seven, Six,
                    Five, Four, Three, Two, One,
                    Empty, Unclicked, Flag, Question,
                    Mine, Incorrect_Flag, Exploded;

    MineGame game;

    BufferedImage buffer;

    public MinePanel(int numCol, int numRow, int numMin) {
        this.numCol = numCol;
        this.numRow = numRow;
        this.numMin = numMin;

        File img = new File("Images");
        File[] imgs = img.listFiles();

        for(File f: imgs) {
            String nam = f.getName().substring(0, f.getName().length() - 4);

        }

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long)upPer);
        } catch (InterruptedException e) {

        }

    }

    public void paint(Graphics g) {

    }

    public void addNotify() {
        super.addNotify();
        Thread t = new Thread(this);
        t.start();
    }
}
