package TicTacToe;

import javax.swing.*;
import java.io.*;

public class Main {
    static int userWins = 0, compWins = 0, gamesPlayed = 1;

    public static void main(String[] args) throws IOException {
        /*FileReader reader = new FileReader("src/TicTacToe/wins.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if(count == 0){
                compWins = Integer.parseInt(line.substring(2,3));
            }
            else if(count==1){
                userWins = Integer.parseInt(line.substring(2,3));
            }
            count++;

        }
        reader.close();*/
        createGame();
    }

    public static void createGame() {
        System.out.println("13:(2o2, 3o1, 1o3) 12:(2x2, 3x1, 1x3) 11:4o 10:4x 9:(2o1,1o2) 8:3o 7:(2x1,1x2) 6:3x 5:1o1 4:2o 3:1x1  2:2x 1:1o 0:rano");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Graphic(frame));
        frame.setResizable(false);
        frame.setTitle("Tic Tac Toe");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}