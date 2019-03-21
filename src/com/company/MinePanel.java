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

    boolean first = true;

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

    public MinePanel(int numCol, int numRow, int numMin) {

        this.numCol = numCol;
        this.numRow = numRow;
        this.numMin = numMin;

        setSize((numCol + 2) * 16, (numRow + 4) * 16);

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

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        ez = new JMenuItem("Easy");
        nm = new JMenuItem("Normal");
        hd = new JMenuItem("Hard");

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
                add(hi);
                add(ex);
            }
        };

        rul = new JMenuItem("Rules");

        abt = new JMenuItem("About");

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

        add(men);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println("Pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //System.out.println(isinGrid(e.getX(), e.getY()));
            }
        });

        addNotify();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep((long) upPer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    public void paint(Graphics g) {

        Graphics g2 = buffer.getGraphics();

        g2.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());

        int y = 48;
        for(MineSpace[] m: game.getBoard().grid) {
            int x = 16;
            for(MineSpace space: m) {
                g2.drawImage(getSquareImage(space), x, y, null);
                x += 16;
            }
            y += 16;
        }
        paintComponents(g2);
        g.drawImage(buffer, 0, 0, null);

    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        Thread t = new Thread(this);
        t.start();
    }

    public boolean isinGrid(int x, int y) {
        return ((x >= 16 && x < (numCol + 1) * 16) && (y >= 48 && y < (numRow + 3) * 16));
    }

    public BufferedImage getSquareImage(MineSpace m)
    {
        switch (m.getState()) {
            case MineSpace.SHOWN:
                switch (m.toString()) {
                    case "-":
                        return images.get("Empty");
                    case "M":
                        return images.get("Mine");
                    default:
                        return images.get(num2Word(m.getNum(), false));
                }
            case MineSpace.UP:
                return images.get("Unclicked");
            case MineSpace.FLAG:
                return images.get("Flag");
            default:
                return images.get("Question");
        }
    }

    private String num2Word(int num, boolean digit) {
        StringBuilder str = new StringBuilder();
        if(digit) {
            str.append("Digit_");
        }
        switch (num) {
            case 1:
                str.append("One");
                break;
            case 2:
                str.append("Two");
                break;
            case 3:
                str.append("Three");
                break;
            case 4:
                str.append("Four");
                break;
            case 5:
                str.append("Five");
                break;
            case 6:
                str.append("Six");
                break;
            case 7:
                str.append("Seven");
                break;
            case 8:
                str.append("Eight");
                break;
            case 9:
                str.append("Nine");
                break;
            default:
                str.append("Zero");
                break;
        }
        return str.toString();
    }
}
