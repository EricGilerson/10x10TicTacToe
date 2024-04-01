package TicTacToe;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;


public class Graphic extends JPanel{

    static int roundX, roundY, compX, compY;
    static int dimenX, dimenY;
    static boolean gameOn, firstComp;
    static int[][] takenBoxes;
    static int moveNum;
    static JFrame fr;
    public static int rounded(int num) {
        return (int) Math.floor(num / 100.0) * 100;
    }
    public Graphic(JFrame frame){
        roundX = -1;roundY = -1;compX = 0; compY = 0;
        dimenX = 10; dimenY = 10;
        takenBoxes = new int[dimenX][dimenY];
        moveNum = 0;gameOn = true;
        fr = frame;
        if(Main.gamesPlayed%2==0){
            firstComp=true;
            compX = ((int)((Math.random()*2)+4)*100);
            compY = ((int)((Math.random()*2)+4)*100);
            takenBoxes[compX / 100][compY / 100] = 2;
        }
        setPreferredSize(new Dimension(dimenX*100, dimenY*100));
        addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){
                if(gameOn) {
                    roundX = rounded(e.getX());
                    roundY = rounded(e.getY());
                    repaint();
                }
                else{
                    fr.setVisible(false);
                    fr.dispose();
                    Main.createGame();
                }
            }
            public void mouseEntered(MouseEvent arg0) {}
            public void mouseExited(MouseEvent arg0) {}
            public void mousePressed(MouseEvent arg0) {}
            public void mouseReleased(MouseEvent arg0) {}
        });
    }
    public static void drawX(int xStart, int yStart, Graphics g){
        //draws the users move as an X if it is possible(not overlapping)
        Graphics2D graphics = (Graphics2D) g;
        Line2D line1 = new Line2D.Float(xStart+10, yStart+10, xStart+90, yStart+90);
        Line2D line2 = new Line2D.Float(xStart+90, yStart+10, xStart+10, yStart+90);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        graphics.draw(line1);
        graphics.draw(line2);
        PatternCheck.fiveInRow(1, g);
    }
    public static void checkBoxTaken(int boxX, int boxY, Graphics g){
        //checks if the box is taken for the user move and executes the user move and the computer thought and then comes back to check if computer move is possible
        boxX = (boxX / 100);
        boxY = (boxY / 100);

        //changes pixels to matrix spots
        if(takenBoxes[boxX][boxY] == 0){
            takenBoxes[boxX][boxY] = 1;
            drawX(roundX, roundY, g);
            if(Main.gamesPlayed%2==0)moveNum++;
            if(moveNum != 0){
                g.setColor(Color.BLACK);
                g.drawOval(compX+7, compY+7,86,86);
            }
            CompMove.CompMove(g);
            if(Main.gamesPlayed%2==1)moveNum++;
        }
        else
            System.out.println("spot already taken");
    }
    @Override
    protected void paintComponent(Graphics g) {
        //creates box
        if(roundX == -1 && roundY == -1) {
            int drawY = 0, drawX = 0;
            for (int i = 0; i < dimenY; i++) {
                for (int j = 0; j < dimenX; j++) {
                    g.drawRect(drawX, drawY, 100, 100);
                    drawX += 100;
                }
                drawY += 100;
                drawX = 0;
            }
            if(!firstComp){
                showMessageDialog(null, "X starts this match");
            }
            if(firstComp){
                showMessageDialog(null, "O starts this match");
                ((Graphics2D) g).setStroke(new BasicStroke(3));
                g.setColor(new Color(56, 62, 224));
                g.drawOval(compX + 7, compY + 7, 86, 86);
                System.out.println(compX +"\t"+compY);
                firstComp = false;
                moveNum++;
            }

        }
        else{
            //draws moves
            checkBoxTaken(roundX, roundY, g);
        }
    }
}
