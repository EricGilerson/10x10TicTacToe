package TicTacToe;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

import static TicTacToe.Graphic.*;
import static TicTacToe.Main.*;
public class PatternCheck{
    static int countVert = 0, countHor = 0, countDia1 = 0,countDia2 = 0;

    public static void fiveInRow(int option, Graphics g){
        countHor = 0;countVert = 0;countDia1 = 0;countDia2 = 0;
        if((moveNum == ((dimenX)*(dimenY)/2)-1 && gamesPlayed%2==1) || (moveNum == ((dimenX)*(dimenY)/2)+1 && gamesPlayed%2==0)) {
            JOptionPane.showMessageDialog(null, "It is a draw\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
            gamesPlayed++;
            gameOn = false;
        }

        first:
        for (int i = 0; i < dimenY; i++) {
            for (int j = 0; j < dimenX; j++) {
                if (takenBoxes[j][i] == option) {
                    countHor++;
                    if (countHor == 5) {
                        if(j<4)countHor = 0;
                        else {
                            System.out.println("5 in a row");
                            gamesPlayed++;
                            if (option == 1) {
                                userWins++;
                                JOptionPane.showMessageDialog(null, "X wins with 5 in a row Horizontally\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                            }
                            else {
                                compWins++;
                                JOptionPane.showMessageDialog(null, "O wins with 5 in a row Horizontally\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                            }
                            /*try {
                                FileWriter writer = new FileWriter("src/TicTacToe/wins.txt", false);
                                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                                bufferedWriter.write("C:" + compWins);
                                bufferedWriter.newLine();
                                bufferedWriter.write("U:" + userWins);

                                bufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/
                            Graphics2D graphics = (Graphics2D) g;
                            g.setColor(Color.RED);
                            ((Graphics2D) g).setStroke(new BasicStroke(4));
                            Line2D winLineHor = new Line2D.Float(((j + 1) * 100) - 25, ((i + 1) * 100) - 50, ((j - 3) * 100) - 75, ((i + 1) * 100) - 50);
                            graphics.draw(winLineHor);
                            ((Graphics2D) g).setStroke(new BasicStroke(3));
                            gameOn = false;
                            break first;
                        }
                    }
                } else countHor = 0;
                if (takenBoxes[i][j] == option) {
                    countVert++;
                    if (countVert == 5) {
                        if(j<4)countVert = 0;
                        else {
                            gamesPlayed++;
                            System.out.println("5 in a row");
                            if (option == 1) {
                                userWins++;
                                JOptionPane.showMessageDialog(null, "X wins with 5 in a row Vertically\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                            } else {
                                compWins++;
                                JOptionPane.showMessageDialog(null, "O wins with 5 in a row Vertically\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                            }
                            /*try {
                                FileWriter writer = new FileWriter("src/TicTacToe/wins.txt", false);
                                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                                bufferedWriter.write("C:" + compWins);
                                bufferedWriter.newLine();
                                bufferedWriter.write("U:" + userWins);

                                bufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }*/
                            Graphics2D graphics = (Graphics2D) g;
                            g.setColor(Color.RED);
                            ((Graphics2D) g).setStroke(new BasicStroke(4));
                            Line2D winLineVert = new Line2D.Float(((i + 1) * 100) - 50, ((j + 1) * 100) - 25, ((i + 1) * 100) - 50, ((j - 3) * 100) - 75);
                            graphics.draw(winLineVert);
                            ((Graphics2D) g).setStroke(new BasicStroke(3));
                            gameOn = false;
                            break first;
                        }
                    }
                } else countVert = 0;
                if (takenBoxes[i][j] == option && i <= 5 && j <= 5) {
                    for (int k = 0; k < 5; k++) {
                        if (takenBoxes[i + k][j + k] == option) {
                            countDia1++;
                            if (countDia1 == 5) {
                                gamesPlayed++;
                                System.out.println("5 in a row");
                                if(option==1){
                                    userWins++;
                                    JOptionPane.showMessageDialog(null, "X wins with 5 in a row Diagonally\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                                }
                                else{
                                    compWins++;
                                    JOptionPane.showMessageDialog(null, "O wins with 5 in a row Diagonally\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                                }
                                /*try {
                                    FileWriter writer = new FileWriter("src/TicTacToe/wins.txt", false);
                                    BufferedWriter bufferedWriter = new BufferedWriter(writer);

                                    bufferedWriter.write("C:" + compWins);
                                    bufferedWriter.newLine();
                                    bufferedWriter.write("U:" + userWins);

                                    bufferedWriter.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }*/
                                Graphics2D graphics = (Graphics2D) g;
                                g.setColor(Color.RED);
                                ((Graphics2D) g).setStroke(new BasicStroke(4));
                                Line2D winLineDia1 = new Line2D.Float(((i+1)*100)-75, ((j+1)*100)-75, ((i+5)*100)-25, ((j+5)*100)-25);
                                graphics.draw(winLineDia1);
                                ((Graphics2D) g).setStroke(new BasicStroke(3));
                                gameOn = false;
                                break first;
                            }
                        } else countDia1 = 0;
                    }
                    countDia1 = 0;
                }
                if (takenBoxes[i][j] == option && j >= 4 && i <= 5) {
                    for (int k = 0; k < 5; k++) {
                        if (takenBoxes[i + k][j - k] == option) {
                            countDia2++;
                            if (countDia2 == 5) {
                                gamesPlayed++;
                                System.out.println("5 in a row");
                                if(option==1) {
                                    userWins++;
                                    JOptionPane.showMessageDialog(null, "X wins with 5 in a row Diagonally\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                                }
                                else {
                                    compWins++;
                                    JOptionPane.showMessageDialog(null, "O wins with 5 in a row Diagonally\nClick anywhere on the board to play again\nUser Wins:  " + userWins + "   Computer Wins:  " + compWins);
                                }
                                /*try {
                                    FileWriter writer = new FileWriter("src/TicTacToe/wins.txt", false);
                                    BufferedWriter bufferedWriter = new BufferedWriter(writer);

                                    bufferedWriter.write("C:" + compWins);
                                    bufferedWriter.newLine();
                                    bufferedWriter.write("U:" + userWins);

                                    bufferedWriter.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }*/
                                Graphics2D graphics = (Graphics2D) g;
                                g.setColor(Color.RED);
                                ((Graphics2D) g).setStroke(new BasicStroke(4));
                                Line2D winLineDia1 = new Line2D.Float(((i+5)*100)-25, ((j-3)*100)-75, ((i+1)*100)-75, ((j+1)*100)-25);
                                graphics.draw(winLineDia1);
                                ((Graphics2D) g).setStroke(new BasicStroke(3));
                                gameOn = false;
                                break first;
                            }
                        } else
                            countDia2 = 0;
                    }
                    countDia2 = 0;
                }
            }
            countHor = 0;countVert = 0;
        }
    }
    public static int[][] horizontalCheck(int amount, int option, int[][] mat, boolean removeCheck) {
        countHor = 0;
        int[][] temp = new int[dimenX][dimenY];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[j][i] == option) {
                    countHor++;
                    if (countHor == amount) {
                        if(j<amount-1)countHor = 0;
                        else {
                            for (int l = 0; l < amount; l++) {
                                temp[j - l][i] = option;
                            }
                        }
                    }
                } else
                    countHor = 0;
            }
            countHor = 0;
        }
        if (!removeCheck){
            temp = removeClosed(amount, option, temp, 1);
        }
        return temp;
    }
    public static int[][] verticalCheck(int amount, int option, int[][] mat, boolean removeCheck){
        countVert = 0;
        int[][] temp = new int[dimenX][dimenY];
        for(int i = 0; i< mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == option) {
                    countVert++;
                    if (countVert == amount) {
                        if (j < amount - 1) countVert = 0;
                        else {
                            for (int l = 0; l < amount; l++) {
                                temp[i][j - l] = option;
                            }
                        }
                    }
                }
                else
                    countVert = 0;
            }
            countVert = 0;
        }
        if (!removeCheck){
            temp = removeClosed(amount, option, temp, 2);
        }
        return temp;
    }
    public static int[][] diagonalDownCheck(int amount, int option, int[][] mat, boolean removeCheck){
        countDia1 = 0;
        int[][] temp = new int[dimenX][dimenY];
        for(int i = 0; i< mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if(mat[i][j] == option && i <= dimenX - amount && j <= dimenY - amount){
                    for(int k=0;k<amount;k++){
                        if(mat[i+k][j+k] == option){
                            countDia1++;
                            if(countDia1 == amount) {
                                for (int l = 0; l < amount; l++) {
                                    temp[i + l][j + l] = option;
                                }
                            }
                        }
                        else
                            countDia1 = 0;
                    }
                    countDia1 = 0;
                }
            }
        }
        if (!removeCheck){
            temp = removeClosed(amount, option, temp, 3);
        }
        return temp;
        }
        public static int[][] diagonalUpCheck(int amount, int option, int[][] mat, boolean removeCheck){
            int[][] temp = new int[dimenX][dimenY];
            countDia2 = 0;
            for(int i =0; i<dimenX;i++){
                for(int j=0;j<dimenY;j++){
                    if(mat[i][j] == option && j >= amount-1 && i <= dimenY - amount){
                        for(int k=0;k<amount;k++){
                            if(mat[i+k][j-k] == option){
                                countDia2++;
                                if(countDia2 == amount) {
                                    for (int l = amount - 1; l >= 0; l--) {
                                        temp[i + l][j - l] = option;
                                    }
                                }
                            }
                            else
                                countDia2 = 0;
                        }
                        countDia2 = 0;
                    }
                }
            }
            if (!removeCheck){
                temp = removeClosed(amount, option, temp, 4);
            }
            return temp;
        }
        public static int[][] removeClosed(int amount, int option, int[][] mat, int pattern){
            int[][] temp = mat;
            //horizontal
            if(pattern == 1){
                for (int i = 0; i < dimenY; i++) {
                    for (int k = 1; k < 5; k++) {
                        if(option == 2) {
                            if (takenBoxes[k][i] == 1) {
                                for (int l = 0; l < k; l++) {
                                    temp[l][i] = 0;
                                }
                            }
                        }
                        else{
                            if (takenBoxes[k][i] == 2) {
                                for (int l = 0; l < k; l++) {
                                    temp[l][i] = 0;
                                }
                            }
                        }
                    }
                    for (int k = dimenX-2; k >= dimenX-5; k--) {
                        if(option == 2) {
                            if (takenBoxes[k][i] == 1) {
                                for (int l = dimenX-1; l > k; l--) {
                                    temp[l][i] = 0;
                                }
                            }
                        }
                        else{
                            if (takenBoxes[k][i] == 2) {
                                for (int l = dimenX-1; l > k; l--) {
                                    temp[l][i] = 0;
                                }
                            }
                        }
                    }
                    for (int j = 1; j < dimenX; j++) {
                        for(int k=0; k<5;k++) {
                            if (j <= dimenX - k-1) {
                                if (option == 1) {
                                    if (takenBoxes[j - 1][i] == 2 && takenBoxes[j + k][i] == 2) {
                                        for (int l = 0; l < k; l++) {
                                            temp[j + l][i] = 0;
                                        }
                                    }
                                } else {
                                    if (takenBoxes[j - 1][i] == 1 && takenBoxes[j + k][i] == 1) {
                                        for (int l = 0; l < k; l++) {
                                            temp[j + l][i] = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //vertical
            if(pattern == 2){
                for (int i = 0; i < dimenX; i++) {
                    for (int k = 1; k < 5; k++) {
                        if(option == 2) {
                            if (takenBoxes[i][k] == 1) {
                                for (int l = 0; l < k; l++) {
                                    temp[i][l] = 0;
                                }
                            }
                        }
                        else{
                            if (takenBoxes[i][k] == 2) {
                                for (int l = 0; l < k; l++) {
                                    temp[i][l] = 0;
                                }
                            }
                        }
                    }
                    for (int k = dimenY-2; k >= dimenY-5; k--) {
                        if(option == 2) {
                            if (takenBoxes[i][k] == 1) {
                                for (int l = dimenY-1; l > k; l--) {
                                    temp[i][l] = 0;
                                }
                            }
                        }
                        else{
                            if (takenBoxes[i][k] == 2) {
                                for (int l = dimenY-1; l > k; l--) {
                                    temp[i][l] = 0;
                                }
                            }
                        }
                    }
                    for (int j = 1; j < dimenY; j++) {
                        for(int k=0;k<5;k++) {
                            if (j <= dimenY - k-1) {
                                if (option == 1) {
                                    if (takenBoxes[i][j-1] == 2 && takenBoxes[i][j+k] == 2) {
                                        for (int l = 0; l < k; l++) {
                                            temp[i][j+l] = 0;
                                        }
                                    }
                                } else {
                                    if (takenBoxes[i][j-1] == 1 && takenBoxes[i][j+k] == 1) {
                                        for (int l = 0; l < k; l++) {
                                            temp[i][j+l] = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //diagonal down
            if(pattern == 3){
                for (int i = 0; i < dimenX; i++) {
                    for (int j = 0; j < dimenY; j++) {
                        for(int k=0;k<4;k++){
                            for(int l=dimenY-4+k;l<dimenY;l++){
                                temp[k][l] = 0;
                            }
                            for(int l=0;l<=k;l++){
                                temp[k+dimenX-4][l] = 0;
                            }
                        }
                        for(int k=1;k<5;k++){
                            if(i==0 && j>=k){
                                if(option == 1) {
                                    if (takenBoxes[k][j] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[k-l][j-l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[k][j] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[k-l][j-l] = 0;
                                        }
                                    }
                                }
                            }
                            if(j==0 && i>=k){
                                if(option == 1) {
                                    if (takenBoxes[i][k] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[i-l][k-l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[i][k] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[i-l][k-l] = 0;
                                        }
                                    }
                                }
                            }
                            if(i==9 && j <= dimenY - k-1){
                                if(option == 1) {
                                    if (takenBoxes[i-k][j] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[i-k+l][j+l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[i-k][j] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[i-k+l][j+l] = 0;
                                        }
                                    }
                                }
                            }
                            if(j==9 && i <= dimenY - k-1){
                                if(option == 1) {
                                    if (takenBoxes[i][j-k] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[i+l][j-k+l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[i][j-k] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[i+l][j-k+l] = 0;
                                        }
                                    }
                                }
                            }
                        }
                        for(int k=1;k<5;k++) {
                            if (j <= dimenY - k-1 && i <= dimenY - k-1 && i>0 && j>0) {
                                if (option == 1) {
                                    if (takenBoxes[i-1][j-1] == 2 && takenBoxes[i+k][j+k] == 2) {
                                        for (int l = 0; l < k; l++) {
                                            temp[i+l][j+l] = 0;
                                        }
                                    }
                                } else {
                                    if (takenBoxes[i-1][j-1] == 1 && takenBoxes[i+k][j+k] == 1) {
                                        for (int l = 0; l < k; l++) {
                                            temp[i+l][j+l] = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(pattern == 4){
                for (int i = 0; i < dimenX; i++) {
                    for (int j = 0; j < dimenY; j++) {
                        for(int k=0;k<4;k++){
                            for(int l=3-k;l>=0;l--){
                                temp[k][l] = 0;
                            }
                            for(int l=dimenY-1-k;l<dimenY;l++){
                                temp[k+dimenX-4][l] = 0;
                            }
                        }
                        for (int k = 1; k < 5; k++) {
                            if(i==0 &&  j<dimenX-k){
                                if(option == 1) {
                                    if (takenBoxes[k][j] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[k-l][j+l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[k][j] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[k-l][j+l] = 0;
                                        }
                                    }
                                }
                            }
                            if(j==0 && i<dimenX-k){
                                if(option == 1) {
                                    if (takenBoxes[i][k] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[i+l][k-l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[i][k] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[i+l][k-l] = 0;
                                        }
                                    }
                                }
                            }
                            if(i==9 && j>=k){
                                if(option == 1) {
                                    if (takenBoxes[i-k][j] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[i-k+l][j-l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[i-k][j] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[i-k+l][j-l] = 0;
                                        }
                                    }
                                }
                            }
                            if(j==9 && i>=k){
                                if(option == 1) {
                                    if (takenBoxes[i][j-k] == 2){
                                        for(int l=1;l<=k;l++){
                                            temp[i-l][j-k+l] = 0;
                                        }
                                    }
                                } else{
                                    if (takenBoxes[i][j-k] == 1){
                                        for(int l=1;l<=k;l++){
                                            temp[i-l][j-k+l] = 0;
                                        }
                                    }
                                }
                            }
                            if(i <= dimenX -k-1 && j>=k && i>0 && j<dimenY-1){
                                if(option == 1) {
                                    if (takenBoxes[i - 1][j + 1] == 2 && takenBoxes[i + k][j - k] == 2) {
                                        for (int l = 0; l < k; l++) {
                                            temp[i + l][j - l] = 0;
                                        }
                                    }
                                }else {
                                    if (takenBoxes[i - 1][j + 1] == 1 && takenBoxes[i + k][j - k] == 1) {
                                        for (int l = 0; l < k; l++) {
                                            temp[i + l][j - l] = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return temp;
        }
}
