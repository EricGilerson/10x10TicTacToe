package TicTacToe;

import java.awt.*;

import static TicTacToe.Graphic.*;

public class CompMove{
    public static void CompMove(Graphics g){
        if(gameOn) {
            if (moveNum != 0) {
                chooseMove.chooseMove();
                if (!canCompMoveHere(compX / 100, compY / 100)) {
                    chooseMove.chooseRandom();
                }
            } else {
                for (int i = 0; i < dimenY; i++) {
                    for (int j = 0; j < dimenX; j++) {
                        if (takenBoxes[i][j] == 1) {
                            if (i < dimenX - 1) {
                                compX = (i + 1) * 100;
                                compY = j * 100;
                            } else if (i == dimenX - 1) {
                                compX = (i - 1) * 100;
                                compY = j * 100;
                            }
                        }
                    }
                }
            }
            takenBoxes[compX / 100][compY / 100] = 2;
            ((Graphics2D) g).setStroke(new BasicStroke(3));
            g.setColor(new Color(56, 62, 224));
            g.drawOval(compX + 7, compY + 7, 86, 86);
            PatternCheck.fiveInRow(2, g);
        }
    }
    public static boolean canCompMoveHere(int x, int y){
        if(x < 0 || x >= dimenX || y < 0 || y >= dimenY)
            return false;
        if (takenBoxes[x][y] != 0)
            return false;
        return true;
    }
}
