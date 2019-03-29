package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class MinePanel extends JPanel {
    int numCol, numRow, numMin;
    double upPer = 1000.0/35.0;

    int sec = 0;

    int fac = 0;

    int dx = -1, dy = -1;

    Map<String, BufferedImage> images = new Map<>();

    MineGame game;

    BufferedImage buffer;

    boolean first = true;

    int min;

    MineSpace held;

    boolean leftDown, rightDown;

    int hx, hy;

    public MinePanel(int numCol, int numRow, int numMin) {

        this.numCol = numCol;
        this.numRow = numRow;
        this.numMin = numMin;

        setSize((numCol + 4) * 16, (numRow + 5) * 16);

        game = new MineGame(numRow, numCol, numMin);

        File img = new File("Images");
        File[] imgs = img.listFiles();

        setLayout(null);

        try {
            for(File f: imgs) {
                String nam = f.getName().substring(0, f.getName().length() - 4);
                images.put(nam, ImageIO.read(f));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(SwingUtilities.isRightMouseButton(e)) {
                    rightDown = true;
                }
                if(SwingUtilities.isLeftMouseButton(e)) {
                    leftDown = true;
                }

                if(isinGrid(e.getX(), e.getY())) {
                    int x = (e.getX() / 16) - 2;
                    int y = (e.getY() / 16) - 3;
                    //System.out.println(x + ", " + y);
                    if (SwingUtilities.isLeftMouseButton(e)) {

                    }
                }

                if(game.getSt() == MineGame.PLAYING) {
                    if ((e.getX() >= getWidth() / 2 - 12 && e.getX() < getWidth() / 2 + 12) && (e.getY() >= 12 && e.getY() < 36)) {
                        fac = 1;
                    }
                }

                if(isinGrid(e.getX(), e.getY())) {
                    dx = (e.getX() / 16) - 2;
                    dy = (e.getY() / 16) - 3;
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if(SwingUtilities.isRightMouseButton(e)) {
                    rightDown = false;
                }
                if(SwingUtilities.isLeftMouseButton(e)) {
                    leftDown = false;
                }

                if(game.getSt() == MineGame.PLAYING) {
                    fac = 0;
                }

                if(isinGrid(e.getX(), e.getY())) {
                    int x = (e.getX() / 16) - 2;
                    int y = (e.getY() / 16) - 3;
                    //System.out.println(x + ", " + y);
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (game.st == MineGame.NOGAME) {
                            game.makeGame(y, x);
                            game.setSt(MineGame.PLAYING);
                        }
                        game.reveal(y, x);
                    }
                }

                if(game.getSt() == MineGame.PLAYING) {
                    if(isinGrid(e.getX(), e.getY()) && SwingUtilities.isRightMouseButton(e)) {
                        int x = (e.getX()/16)-2;
                        int y = (e.getY()/16)-3;

                        switch (game.getMap().grid[y][x].getState()) {
                            case MineSpace.UP:
                                game.getMap().grid[y][x].setState(MineSpace.FLAG);
                                game.setMa(game.ma + 1);
                                break;
                            case MineSpace.FLAG:
                                game.getMap().grid[y][x].setState(MineSpace.QUESTION);
                                game.setMa(game.ma - 1);
                                break;
                            case MineSpace.QUESTION:
                                game.getMap().grid[y][x].setState(MineSpace.UP);
                                break;
                        }
                    }
                }

                dx = -1; dy = -1;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(isinGrid(e.getX(), e.getY())) {
                    dx = (e.getX() / 16) - 2;
                    dy = (e.getY() / 16) - 3;
                }
                //System.out.println(dx + ", " + dy);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //System.out.println(isinGrid(e.getX(), e.getY()));
            }
        });

        //addNotify();
    }

    public void update() {
        if(game.getSt() == MineGame.PLAYING) {
            sec = (int)((System.nanoTime() - game.sTime)/1000000000);
        }

        if(game.getSt() == MineGame.LOSE) {
            sec = (int)(game.ds - (game.sTime/1000000000));
            fac = 3;
        }

        if(game.getSt() == MineGame.WIN) {
            sec = (int)(game.ds - (game.sTime/1000000000));
            fac = 4;
        }

        min = (game.mi - game.ma);

        if(dy != -1 && dx != -1)
            held = game.map.grid[dy][dx];
        else {
            held = null;
        }
    }

    public void paint(Graphics g) {

        Graphics g2 = buffer.getGraphics();
        g2.setColor(Color.LIGHT_GRAY);

        g2.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());

        hx = -1; hy = -1;
        int y = 48;
        for(MineSpace[] m: game.getBoard().grid) {
            int x = 32;
            for(MineSpace space: m) {
                if(held != null && space.equals(held) && space.getState() == MineSpace.UP) {
                    hx = x; hy = y;
                } else {
                    g2.drawImage(getSquareImage(space), x, y, null);
                }
                x += 16;
            }
            y += 16;
        }

        if(hx != -1 && hy != -1) {
            if(leftDown && rightDown) {
                for(int xg = dx - 1; xg <= dx + 1; xg++) {
                    for(int yg = dy - 1; yg <= dy + 1; yg++) {
                        if(xg == 0 && yg == 0) {
                            continue;
                        }

                    }
                }
                for (int gx = hx - 16; gx <= hx + 16; gx += 16) {
                    for (int gy = hy - 16; gy <= hy + 16; gy += 16) {
                        System.out.println(gx + ", " + gy);
                        if(gy >= (numRow + 3)*16 || gx >= (numCol + 2)*16)
                            continue;
                        g2.drawImage(images.get("Down"), gx, gy, null);
                    }
                }
            } else {
                g2.drawImage(images.get("Down"), hx, hy, null);
            }
        }

        switch (fac) {
            case 0:
                g2.drawImage(images.get("Happy"), (getWidth()/2) - 12, 12, null);
                break;
            case 1:
                g2.drawImage(images.get("Happy_Down"), (getWidth()/2) - 12, 12, null);
                break;
            case 2:
                g2.drawImage(images.get("Oh"), (getWidth()/2) - 12, 12, null);
                break;
            case 3:
                g2.drawImage(images.get("Dead"), (getWidth()/2) - 12, 12, null);
                break;
            case 4:
                g2.drawImage(images.get("Shades"), (getWidth()/2) - 12, 12, null);
                break;

        }
        for(int i = 1; i <= 4; i++) {
            g2.drawImage(images.get(num2Word(sec % 10, true)), (numCol + 2) * 16 - (12 * i) - 1, 12,  null);
            sec /= 10;
        }

        if(min < 0) {
            g2.drawImage(images.get("Digit_Hyphen"), 32, 12, null);
        } else {
            g2.drawImage(images.get("Digit_Zero"), 32, 12, null);
        }

        int m = min;

        for(int i = 1; i < 4; i++) {
            g2.drawImage(images.get(num2Word(m % 10, true)), 80 - i * 12, 12, null);
            m /= 10;
        }
        paintComponents(g2);
        g.drawImage(buffer, 0, 0, null);

    }

    public boolean isinGrid(int x, int y) {
        return ((x >= 32 && x < (numCol + 2) * 16) && (y >= 48 && y < (numRow + 3) * 16));
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
