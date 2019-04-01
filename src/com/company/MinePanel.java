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

    boolean leftDown, rightDown, middleDown;

    int hx, hy;

    int cx, cy;

    int[] tim = new int[4];

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
                if(SwingUtilities.isMiddleMouseButton(e)) {
                    middleDown = true;
                }

                if(isinGrid(e.getX(), e.getY())) {
                    cx = (e.getX() / 16) - 2;
                    cy = (e.getY() / 16) - 3;
                    //System.out.println(x + ", " + y);
                    if (SwingUtilities.isLeftMouseButton(e)) {

                    }
                }

                if(game.getSt() == MineGame.PLAYING) {
                    if ((e.getX() >= getWidth() / 2 - 12 && e.getX() < getWidth() / 2 + 12) && (e.getY() >= 12 && e.getY() < 36)) {
                        fac = 1;
                    } else if(isinGrid(e.getX(), e.getY())) {
                        fac = 2;
                    }
                }

                if(isinGrid(e.getX(), e.getY()) && game.getSt() == MineGame.PLAYING) {
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
                if(SwingUtilities.isMiddleMouseButton(e)) {
                    middleDown = false;
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
                    if(isinGrid(e.getX(), e.getY()) && SwingUtilities.isMiddleMouseButton(e)) {
                        int mar = 0;
                        for(int xx = dx - 1; xx <= dx + 1; xx++) {
                            for(int yy = dy - 1; yy <= dy + 1; yy++) {
                                if(yy >= game.map.grid.length || yy < 0 || xx >= game.map.grid[0].length || xx < 0) {
                                    continue;
                                }
                                if(yy == 0 && xx == 0) {
                                    continue;
                                }
                                if(game.map.grid[yy][xx].getState() == MineSpace.FLAG) {
                                    mar++;
                                }
                            }
                        }
                        if(game.map.grid[dy][dx].getState() == MineSpace.SHOWN && game.map.grid[dy][dx].getNum() == mar) {
                            for (int xg = dx - 1; xg <= dx + 1; xg++) {
                                for (int yg = dy - 1; yg <= dy + 1; yg++) {
                                    if (xg == 0 && yg == 0) {
                                        continue;
                                    }
                                    if (game.map.grid[yg][xg].getState() == MineSpace.FLAG || game.map.grid[yg][xg].getState() == MineSpace.SHOWN)
                                        continue;
                                    if (yg >= game.map.grid.length || yg < 0 || xg >= game.map.grid[0].length || xg < 0) {
                                        continue;
                                    }
                                    game.reveal(yg, xg);
                                }
                            }
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
                if(isinGrid(e.getX(), e.getY()) && game.getSt() == MineGame.PLAYING) {
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

        for(int i = 0; i < 4; i++) {
            tim[i] = sec % 10;
            sec /= 10;
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

                if(game.getSt() == MineGame.LOSE) {
                    if(x == (cx + 2) * 16 && y == (cy + 3) * 16 && game.map.grid[cy][cx].isMine())
                        g2.drawImage(images.get("Exploded"), x, y, null);
                    if(game.map.grid[(y/16)-3][x/16-2].getState() == MineSpace.FLAG && !game.map.grid[y/16-3][x/16-2].isMine()) {
                        g2.drawImage(images.get("IncorrectFlag"), x, y, null);
                    }
                    if(game.map.grid[(y/16)-3][x/16-2].getState() == MineSpace.FLAG && game.map.grid[y/16-3][x/16-2].isMine()) {
                        g2.drawImage(images.get("Flag"), x, y, null);
                    }
                }

                x += 16;
            }
            y += 16;
        }

        if(middleDown) {
            for(int xg = dx - 1; xg <= dx + 1; xg++) {
                for(int yg = dy - 1; yg <= dy + 1; yg++) {
                    if(xg == 0 && yg == 0) {
                        continue;
                    }
                    if(game.map.grid[yg][xg].getState() == MineSpace.FLAG || game.map.grid[yg][xg].getState() == MineSpace.SHOWN)
                        continue;
                    if(yg >= game.map.grid.length || yg < 0 || xg >= game.map.grid[0].length || xg < 0) {
                        continue;
                    }
                    g2.drawImage(images.get("Down"), (xg + 2) * 16, (yg + 3) * 16, null);
                }
            }
        }

        if(hx != -1 && hy != -1) {
            if(middleDown) {
                for(int xg = dx - 1; xg <= dx + 1; xg++) {
                    for(int yg = dy - 1; yg <= dy + 1; yg++) {
                        if(xg == 0 && yg == 0) {
                            continue;
                        }

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
            g2.drawImage(images.get(num2Word(tim[i - 1], true)), (numCol + 2) * 16 - (12 * i) - 1, 12,  null);
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
