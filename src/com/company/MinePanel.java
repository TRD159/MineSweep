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

    Map<String, BufferedImage> images = new Map<>();

    MineGame game;

    BufferedImage buffer;

    public MinePanel(int numCol, int numRow, int numMin) {
        this.numCol = numCol;
        this.numRow = numRow;
        this.numMin = numMin;

        setSize(numCol * 16 + 96, numRow * 16 + 96);

        game = new MineGame(numRow, numCol, numMin);

        File img = new File("Images");
        File[] imgs = img.listFiles();

        try {
            for(File f: imgs) {
                String nam = f.getName().substring(0, f.getName().length() - 4);
                images.put(nam, ImageIO.read(f));
            }
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
            e.printStackTrace();
        }
        repaint();
    }

    public void paint(Graphics g) {
        Graphics g2 = buffer.getGraphics();

    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        Thread t = new Thread(this);
        t.start();
    }
}
