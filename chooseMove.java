package TicTacToe;

import static TicTacToe.CompMove.*;
import static TicTacToe.Graphic.*;
import static TicTacToe.PatternCheck.*;

public class chooseMove{
    static int countS = 0, countE = 0;
    static int[][] patternH, patternV, patternD1, patternD2;
    static int numH = 0, numV = 0, numD1 = 0,numD2 = 0;
    static boolean coveredH, coveredV, coveredD1, coveredD2;
    static String[] moves = {"rano", "1o", "2x",  "1x1", "2o", "1o1", "3x", "(2x1,1x2)", "3o", "(2o1,1o2)", "4x", "4o", "(2x2, 3x1, 1x3)", "(2o2, 3o1, 1o3)"};
    public static void chooseRandom(){
        boolean found = false;
        while(!found){
            compX = Graphic.rounded((int) (Math.random() * 1000));
            compY = Graphic.rounded((int) (Math.random() * 1000));
            if (canCompMoveHere(compX / 100, compY / 100)){
                found = true;
            }
        }
    }
    public static void chooseMove(){
        numH = 0;numV = 0;numD1 = 0;numD2 = 0;
        hor:
        for(int i=4; i>0; i--){
            for(int j=2;j>0;j--){
                patternH = horizontalCheck(i, j, takenBoxes, false);
                if(i!=1) {
                    if (checkSpots(patternH, i, j) > numH) {
                        numH = checkSpots(patternH, i, j);
                        if(numH<8)
                            checkCovered(numH/2, (numH%2)+1, patternH, 1);
                        break hor;
                    }
                }
                else if(j == 2){
                    if (checkSpots(patternH, 1, 2) > numH) {
                        numH = checkSpots(patternH, 1, 2);
                        checkCovered(1,2,patternH,1);
                        break hor;
                    }
                }
            }
        }
        vert:
        for(int i=4; i>0; i--){
            for(int j=2;j>0;j--){
                patternV = verticalCheck(i, j, takenBoxes, false);
                if(i!=1) {
                    if (checkSpots(patternV, i, j) > numV) {
                        numV = checkSpots(patternV, i, j);
                        if(numV<8)
                            checkCovered(numV/2, (numV%2)+1, patternV, 2);
                        break vert;
                    }
                }
                else if(j == 2){
                    if (checkSpots(patternV, 1, 2) > numV) {
                        numV = checkSpots(patternV, 1, 2);
                        checkCovered(1,2,patternV,2);
                        break vert;
                    }
                }
            }
        }
        diaDown:
        for(int i=4; i>0; i--){
            for(int j=2;j>0;j--){
                patternD1 = diagonalDownCheck(i, j, takenBoxes, false);
                if(i!=1) {
                    if (checkSpots(patternD1, i, j) > numD1) {
                        numD1 = checkSpots(patternD1, i, j);
                        if(numD1<8)
                            checkCovered(numD1/2, (numD1%2)+1, patternD1, 3);
                        break diaDown;
                    }
                }
                else if(j == 2){
                    if (checkSpots(patternD1, 1, 2) > numD1) {
                        numD1 = checkSpots(patternD1, 1, 2);
                        checkCovered(1, 2, patternD1, 3);
                        break diaDown;
                    }
                }
            }
        }
        diaUp:
        for(int i=4; i>0; i--){
            for(int j=2;j>0;j--){
                patternD2 = diagonalUpCheck(i, j, takenBoxes, false);
                if(i!=1) {
                    if (checkSpots(patternD2, i, j) > numD2) {
                        numD2 = checkSpots(patternD2, i, j);
                        if(numD2<8)
                            checkCovered(numD2/2, (numD2%2)+1, patternD2, 4);
                        break diaUp;
                    }
                }
                else if(j == 2) {
                    if (checkSpots(patternD2, 1, 2) > numD2){
                        numD2 = checkSpots(patternD2, 1, 2);
                        checkCovered(1, 2, patternD2, 4);
                        break diaUp;
                    }
                }
            }
        }
        if (numH == 3) numH = 1;else if (numH == 5) numH = 4;else if (numH == 4) numH = 2;else if(numH>=8)numH+=2;else if(numH==7)numH=8;
        if (numV == 3) numV = 1;else if (numV == 5) numV = 4;else if (numV == 4) numV = 2;else if(numV>=8)numV+=2;else if(numV==7)numV=8;
        if (numD1 == 3) numD1 = 1;else if (numD1 == 5) numD1 = 4;else if (numD1 == 4) numD1 = 2;else if(numD1>=8)numD1+=2;else if(numD1==7)numD1=8;
        if (numD2 == 3) numD2 = 1;else if (numD2 == 5) numD2 = 4;else if (numD2 == 4) numD2 = 2;else if(numD2>=8)numD2+=2;else if(numD2==7)numD2=8;
        if(!spotWinMiddle() || (numH == 11 || numV == 11 || numD1 == 11 || numD2 == 11)) {
            if((9<numH || 9<numV || 9<numD1 || 9<numD2) || (betweenOneATwo(5) < numH || betweenOneATwo(5) < numV || betweenOneATwo(5) < numD1 || betweenOneATwo(5) < numD2)) {
                if((5<numH || 5<numV || 5<numD1 || 5 <numD2) ||(betweenTwoOnes(5) < numH || betweenTwoOnes(5) < numV || betweenTwoOnes(5) < numD1 || betweenTwoOnes(5) < numD2)) {
                    if ((numH + numV + numD1 + numD2) == 0) {
                        chooseRandom();
                    }  else if ((numH > numV && numH > numD1 && numH > numD2) || (numH >= numV && numH >= numD1 && numH >= numD2 && !coveredH) ||
                            ((numH == numV && numH == numD1  && numH == numD2 && coveredV && coveredD1 && coveredD2) || (numH == numV && numH == numD1  && numH > numD2 && coveredV && coveredD1) || (numH == numV && numH == numD2  && numH > numD1 && coveredV && coveredD2) || (numH == numD2 && numH == numD1  && numH > numV && coveredD2 && coveredD1) ||
                                    (numH == numV && numH > numD1  && numH > numD2 && coveredV) || (numH == numD1 && numH > numV  && numH > numD2 && coveredD1) || (numH == numD2 && numH > numD1  && numH > numV && coveredD2))) {
                        if (numH == 1) numH = 3;else if (numH == 4) numH = 5;else if (numH == 2) numH = 4;else if (numH == 11 || numH == 10) numH -= 2;else if(numH==8)numH=7;
                        placeSpots(patternH, numH / 2, (numH % 2) + 1, 1);
                        if (numH == 3) numH = 1;else if (numH == 5) numH = 4;else if (numH == 4) numH = 2;else if (numH >= 8) numH += 2;else if(numH==7)numH=8;
                    } else if((numV > numD1 && numV > numD2) || ((numV >= numD1 && numV >= numD2 && !coveredV)) || ((numV == numD1 && numV == numD2 && coveredD1 && coveredD2) || (numV == numD1 && numV > numD2 && coveredD1) || (numV == numD2 && numV > numD1 && coveredD2))) {
                        if (numV == 1) numV = 3;else if (numV == 4) numV = 5;else if (numV == 2) numV = 4;else if (numV == 11 || numV == 10) numV -= 2;else if(numV==8)numV=7;
                        placeSpots(patternV, numV / 2, (numV % 2) + 1, 2);
                        if (numV == 3) numV = 1;else if (numV == 5) numV = 4;else if (numV == 4) numV = 2;else if (numV >= 8) numV += 2;else if(numV==7)numV=8;
                    } else if (numD1 > numD2 || ((numD1 >= numD2) && !coveredD1) || ((numD1 == numD2 && coveredD2))) {
                        if (numD1 == 1) numD1 = 3;else if (numD1 == 4) numD1 = 5;else if (numD1 == 2) numD1 = 4;else if (numD1 == 11 || numD1 == 10) numD1 -= 2;else if(numD1==8)numD1=7;
                        placeSpots(patternD1, numD1 / 2, (numD1 % 2) + 1, 3);
                        if (numD1 == 3) numD1 = 1;else if (numD1 == 5) numD1 = 4;else if (numD1 == 4) numD1 = 2;else if (numD1 >= 8) numD1 += 2;else if(numD1==7)numD1=8;
                    } else {
                        if (numD2 == 1) numD2 = 3;else if (numD2 == 4) numD2 = 5;else if (numD2 == 2) numD2 = 4;else if (numD2 == 11 || numD2 == 10) numD2 -= 2;else if(numD2==8)numD2=7;
                        placeSpots(patternD2, numD2 / 2, (numD2 % 2) + 1, 4);
                        if (numD2 == 3) numD2 = 1;else if (numD2 == 5) numD2 = 4;else if (numD2 == 4) numD2 = 2;else if (numD2 >= 8) numD2 += 2;else if(numD2==7)numD2=8;
                    }
                }
                else{
                    if(betweenTwoOnes(1)>=numH) {
                        numH = betweenTwoOnes(1);
                    }
                    else if(betweenTwoOnes(2)>=numV) {
                        numV = betweenTwoOnes(2);
                    }
                    else if(betweenTwoOnes(3)>=numD1) {
                        numD1 = betweenTwoOnes(3);
                    }
                    else if(betweenTwoOnes(4)>=numD2) {
                        numD2 = betweenTwoOnes(4);
                    }
                }
            }
            else{
                if(betweenOneATwo(1)>=numH) {
                    numH = betweenOneATwo(1);
                }
                else if(betweenOneATwo(2)>=numV) {
                    numV = betweenOneATwo(2);
                }
                else if(betweenOneATwo(3)>=numD1) {
                    numD1 = betweenOneATwo(3);
                }
                else if(betweenOneATwo(4)>=numD2) {
                    numD2 = betweenOneATwo(4);
                }
            }
        }
        System.out.println(moveNum + ": " + " H: " + numH +" V: " + numV   + " D1: " + numD1 + " D2: " + numD2);
    }
    public static void checkCovered(int amount, int option, int[][] mat, int pattern){
        int[][] temp = new int[dimenX][dimenY];
        if(pattern == 1){
            coveredH = false;
            hor:
            for(int l=0;l<1;l++) {
                for (int i = 0; i < dimenX; i++) {
                    for (int j = 0; j < dimenY; j++) {
                        if (i > 0 && i < dimenX - amount) {
                            if (mat[i][j] == option && takenBoxes[i - 1][j] == 0 && takenBoxes[i + amount][j] == 0) {
                                for (int k = 0; k < amount; k++) {
                                    temp[i + k][j] = option;
                                }
                                patternH = temp;
                                break hor;
                            }
                        }
                    }
                }
                coveredH = true;
            }
        }
        if(pattern == 2){
            coveredV = false;
            vert:
            for(int l=0;l<1;l++) {
                for (int i = 0; i < dimenX; i++) {
                    for (int j = 0; j < dimenY; j++) {
                        if (j > 0 && j < dimenY - amount) {
                            if (mat[i][j] == option && takenBoxes[i][j-1] == 0 && takenBoxes[i][j+amount] == 0) {
                                for (int k = 0; k < amount; k++) {
                                    temp[i][j+k] = option;
                                }
                                patternV = temp;
                                break vert;
                            }
                        }
                    }
                }
                coveredV = true;
            }
        }
        if(pattern == 3){
            coveredD1 = false;
            diaDown:
            for(int l=0;l<1;l++) {
                for (int i = 0; i < dimenX; i++) {
                    for (int j = 0; j < dimenY; j++) {
                        if (j > 0 && i>0 && j < dimenY - amount && i < dimenX - amount) {
                            if (mat[i][j] == option && takenBoxes[i-1][j-1] == 0 && takenBoxes[i+amount][j+amount] == 0) {
                                for (int k = 0; k < amount; k++) {
                                    temp[i+k][j+k] = option;
                                }
                                patternD1 = temp;
                                break diaDown;
                            }
                        }
                    }
                }
                coveredD1 = true;
            }
        }
        if(pattern == 4){
            coveredD2 = false;
            diaUp:
            for(int l=0;l<1;l++) {
                for (int i = 0; i < dimenX; i++) {
                    for (int j = 0; j < dimenY; j++) {
                        if (j >= amount && i>0 && j < dimenY-1 && i < dimenX - amount) {
                            if (mat[i][j] == option && takenBoxes[i-1][j+1] == 0 && takenBoxes[i+amount][j-amount] == 0) {
                                for (int k = 0; k < amount; k++) {
                                    temp[i+k][j-k] = option;
                                }
                                patternD2 = temp;
                                break diaUp;
                            }
                        }
                    }
                }
                coveredD2 = true;
            }
        }
    }
    public static boolean checkCovMiddle(int amount, int option){
        int[][] matH = horizontalCheck(amount, option, takenBoxes, false);
        int[][] matV = verticalCheck(amount, option, takenBoxes, false);
        int[][] matD1 = diagonalDownCheck(amount, option, takenBoxes, false);
        int[][] matD2 = diagonalUpCheck(amount, option, takenBoxes, false);

        for (int i = 0; i < dimenX; i++) {
            for (int j = 0; j < dimenY; j++) {
                if (i > 0 && i < dimenX - amount) {
                    if (matH[i][j] == option && takenBoxes[i - 1][j] == 0 && takenBoxes[i + amount][j] == 0) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < dimenX; i++) {
            for (int j = 0; j < dimenY; j++) {
                if (j > 0 && j < dimenY - amount) {
                    if (matV[i][j] == option && takenBoxes[i][j-1] == 0 && takenBoxes[i][j+amount] == 0) {
                            return true;
                    }
                }
            }
        }
        for (int i = 0; i < dimenX; i++) {
            for (int j = 0; j < dimenY; j++) {
                if (j > 0 && i>0 && j < dimenY - amount && i < dimenX - amount) {
                    if (matD1[i][j] == option && takenBoxes[i-1][j-1] == 0 && takenBoxes[i+amount][j+amount] == 0) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < dimenX; i++) {
            for (int j = 0; j < dimenY; j++) {
                if (j >= amount && i>0 && j < dimenY-1 && i < dimenX - amount) {
                    if (matD2[i][j] == option && takenBoxes[i-1][j+1] == 0 && takenBoxes[i+amount][j-amount] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void horizontalEdges(int j, int amount){
        countS = 0;
        countE = 0;
        if(j == 0 || j == (dimenX-amount-1)) {
            for (int k = 0; k < dimenX; k++) {
                for (int l = 0; l < amount; l++) {
                    if (takenBoxes[j + l][k] == takenBoxes[9 - (amount - 1) + l][k])
                        countE++;
                    if (takenBoxes[j + l][k] == takenBoxes[l][k])
                        countS++;
                }
            }
        }
    }
    public static void verticalEdges(int i, int amount){
        countS = 0;
        countE = 0;
        if(i == 0 || i == (dimenY-amount-1)) {
            for (int k = 0; k < dimenY; k++) {
                for (int l = 0; l < amount; l++) {
                    if (takenBoxes[k][i + l] == takenBoxes[k][9 - (amount - 1) + l])
                        countE++;
                    if (takenBoxes[k][i + l] == takenBoxes[k][l])
                        countS++;
                }
            }
        }
    }
    public static int checkSpots(int[][] patternMat, int amount, int option){
        for (int i = 0; i < dimenX; i++) {
            for (int j = 0; j < dimenY; j++) {
                if (patternMat[i][j] == option) {
                    return (amount * 2) + (option - 1);
                }
            }
        }
        return 0;
    }
    public static void placeSpots(int[][] patternMat, int amount, int option, int pattern){
        if(pattern == 1) {
            hor:
            for (int i = 0; i < dimenY; i++) {
                for (int j = 0; j < dimenX - amount + 1; j++) {
                    if (patternMat[j][i] == option) {
                        compY = (i) * 100;
                        horizontalEdges(j, amount);
                        if (countE == dimenX * amount) {
                            compX = (j - 1) * 100;
                        }
                        else if (countS == dimenX * 4) {
                            compX = (j + amount) * 100;
                        }
                        else {
                            compX = (j + amount) * 100;
                            if (!canCompMoveHere(compX / 100, compY / 100)) {
                                compX = (j - 1) * 100;
                            }
                        }
                        break hor;
                    }
                }
            }
        }
        if(pattern == 2) {
            vert:
            for (int i = 0; i < dimenX; i++) {
                for (int j = 0; j < dimenY - amount + 1; j++) {
                    if (patternMat[i][j] == option) {
                        compX = (i) * 100;
                        verticalEdges(j, amount);
                        if (countE == dimenY * amount) {
                            compY = (j - 1) * 100;
                        }
                        else if (countS == dimenY * 4) {
                            compY = (j + amount) * 100;
                        }
                        else {
                            compY = (j - 1) * 100;
                            if (!canCompMoveHere(compX / 100, compY / 100)) {
                                compY = (j + amount) * 100;
                            }
                        }
                        break vert;
                    }
                }
            }
        }
        if(pattern == 3){
            diaDown:
            for(int i=0;i<dimenX;i++){
                for(int j=0;j<dimenY;j++){
                    if(patternMat[i][j] == option){
                        if(i+amount-1==9 && j+amount-1== 9){
                            compX = (i-amount+1) * 100;
                            compY = (j-amount+1) * 100;
                        }
                        else if(j==0 && i+amount<dimenX){
                            compX = (i + amount) * 100;
                            compY = (j + amount) * 100;
                        }
                        else if(i==0 && j+amount<dimenY){
                            compY = (j + amount) * 100;
                            compX = (i + amount) * 100;
                        }
                        else if(j+amount-1==9){
                            compX = (i-1) * 100;
                            compY = (j-1) * 100;
                        }
                        else if(i+amount-1==9){
                            compY = (j-1) * 100;
                            compX = (i-1) * 100;
                        }
                        else{
                            compY = (j + amount) * 100;
                            compX = (i + amount) * 100;
                            if(!canCompMoveHere(compX/100,compY/100)){
                                compY = (j - 1) * 100;
                                compX = (i - 1) * 100;
                            }
                        }
                        break diaDown;
                    }
                }
            }
        }
        if(pattern == 4){
            diaUp:
            for(int i=0;i<dimenX;i++){
                for(int j=0;j<dimenY;j++){
                    if(patternMat[i][j] == option){
                        if(i+amount-1==9 && j-amount+1== 0){
                            compX = (i-amount+1) * 100;
                            compY = (j+amount-1) * 100;
                        }
                        else if(i==0 && j-amount>=0){
                            compY = (j - amount) * 100;
                            compX = (i + amount) * 100;
                        }
                        else if(j==9 && i-amount>=0){
                            compX = (i + amount) * 100;
                            compY = (j - amount) * 100;
                        }
                        else if(j-amount+1==0){
                            compX = (i-1) * 100;
                            compY = (j+1) * 100;
                        }
                        else if(i+amount-1==9){
                            compY = (j+1) * 100;
                            compX = (i-1) * 100;
                        }
                        else{
                            compX = (i - 1) * 100;
                            compY = (j + 1) * 100;
                            if(!canCompMoveHere(compX/100,compY/100)){
                                compX = (i + amount) * 100;
                                compY = (j - amount) * 100;
                            }
                        }
                        break diaUp;
                    }
                }
            }
        }
    }
    public static boolean spotWinMiddle(){
        horO:
        for(int i=0;i<=dimenY-5;i++){
            for(int j=0;j<dimenX;j++){
                if(takenBoxes[i][j] == 2 && takenBoxes[i + 1][j] == 2 && takenBoxes[i+2][j] == 0 && takenBoxes[i+3][j] == 2 && takenBoxes[i+4][j] == 2){
                    compY = j * 100;
                    compX = (i+2) * 100;
                    numH = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i + 1][j] == 2 && takenBoxes[i+2][j] == 2 && takenBoxes[i+3][j] == 0 && takenBoxes[i+4][j] == 2){
                    compY = j * 100;
                    compX = (i+3) * 100;
                    numH = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i + 1][j] == 0 && takenBoxes[i+2][j] == 2 && takenBoxes[i+3][j] == 2 && takenBoxes[i+4][j] == 2){
                    compY = j * 100;
                    compX = (i+1) * 100;
                    numH = 13;
                    return true;
                }
            }
        }
        horX:
        for(int i=0;i<=dimenY-5;i++){
            for(int j=0;j<dimenX;j++){
                if(takenBoxes[i][j] == 1 && takenBoxes[i + 1][j] == 1 && takenBoxes[i+2][j] == 0 && takenBoxes[i+3][j] == 1 && takenBoxes[i+4][j] == 1){
                    compY = j * 100;
                    compX = (i+2) * 100;
                    numH = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i + 1][j] == 1 && takenBoxes[i+2][j] == 1 && takenBoxes[i+3][j] == 0 && takenBoxes[i+4][j] == 1){
                    compY = j * 100;
                    compX = (i+3) * 100;
                    numH = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i + 1][j] == 0 && takenBoxes[i+2][j] == 1 && takenBoxes[i+3][j] == 1 && takenBoxes[i+4][j] == 1){
                    compY = j * 100;
                    compX = (i+1) * 100;
                    numH = 12;
                    return true;
                }
            }
        }
        vertO:
        for(int i=0;i<dimenX;i++){
            for(int j=0;j<=dimenY-5;j++){
                if(takenBoxes[i][j] == 2 && takenBoxes[i][j + 1] == 2 && takenBoxes[i][j+2] == 0 && takenBoxes[i][j+3] == 2 && takenBoxes[i][j+4] == 2){
                    compY = (j+2) * 100;
                    compX = i * 100;
                    numV = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i][j + 1] == 2 && takenBoxes[i][j+2] == 2 && takenBoxes[i][j+3] == 0 && takenBoxes[i][j+4] == 2){
                    compY = (j+3) * 100;
                    compX = i * 100;
                    numV = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i][j + 1] == 0 && takenBoxes[i][j+2] == 2 && takenBoxes[i][j+3] == 2 && takenBoxes[i][j+4] == 2){
                    compY = (j+1) * 100;
                    compX = i * 100;
                    numV = 13;
                    return true;
                }
            }
        }
        vertX:
        for(int i=0;i<dimenY;i++){
            for(int j=0;j<=dimenX-5;j++){
                if(takenBoxes[i][j] == 1 && takenBoxes[i][j + 1] == 1 && takenBoxes[i][j + 2] == 0 && takenBoxes[i][j+3] == 1 && takenBoxes[i][j+4] == 1){
                    compY = (j+2) * 100;
                    compX = i * 100;
                    numV = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i][j+1] == 1 && takenBoxes[i][j+2] == 1 && takenBoxes[i][j+3] == 0 && takenBoxes[i][j+4] == 1){
                    compY = (j+3) * 100;
                    compX = i * 100;
                    numV = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i][j+1] == 0 && takenBoxes[i][j+2] == 1 && takenBoxes[i][j+3] == 1 && takenBoxes[i][j+4] == 1){
                    compY = (j+1) * 100;
                    compX = i * 100;
                    numV = 12;
                    return true;
                }
            }
        }
        Dia1O:
        for(int i=0;i<=dimenY-5;i++) {
            for (int j = 0; j <= dimenX - 5; j++) {
                if(takenBoxes[i][j] == 2 && takenBoxes[i+1][j + 1] == 2 && takenBoxes[i+2][j + 2] == 0 && takenBoxes[i+3][j+3] == 2 && takenBoxes[i+4][j+4] == 2){
                    compY = (j+2) * 100;
                    compX = (i+2) * 100;
                    numD1 = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i+1][j+1] == 2 && takenBoxes[i+2][j+2] == 2 && takenBoxes[i+3][j+3] == 0 && takenBoxes[i+4][j+4] == 2){
                    compY = (j+3) * 100;
                    compX = (i+3) * 100;
                    numD1 = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i+1][j+1] == 0 && takenBoxes[i+2][j+2] == 2 && takenBoxes[i+3][j+3] == 2 && takenBoxes[i+4][j+4] == 2){
                    compY = (j+1) * 100;
                    compX = (i+1) * 100;
                    numD1 = 13;
                    return true;
                }
            }
        }
        Dia1X:
        for(int i=0;i<=dimenY-5;i++) {
            for (int j = 0; j <= dimenX - 5; j++) {
                if(takenBoxes[i][j] == 1 && takenBoxes[i+1][j + 1] == 1 && takenBoxes[i+2][j + 2] == 0 && takenBoxes[i+3][j+3] == 1 && takenBoxes[i+4][j+4] == 1){
                    compY = (j+2) * 100;
                    compX = (i+2) * 100;
                    numD1 = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i+1][j+1] == 1 && takenBoxes[i+2][j+2] == 1 && takenBoxes[i+3][j+3] == 0 && takenBoxes[i+4][j+4] == 1){
                    compY = (j+3) * 100;
                    compX = (i+3) * 100;
                    numD1 = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i+1][j+1] == 0 && takenBoxes[i+2][j+2] == 1 && takenBoxes[i+3][j+3] == 1 && takenBoxes[i+4][j+4] == 1){
                    compY = (j+1) * 100;
                    compX = (i+1) * 100;
                    numD1 = 12;
                    return true;
                }
            }
        }
        Dia2O:
        for(int i=0;i<=dimenY-5;i++) {
            for (int j = dimenY-1; j >= 4; j--) {
                if(takenBoxes[i][j] == 2 && takenBoxes[i+1][j - 1] == 2 && takenBoxes[i+2][j - 2] == 0 && takenBoxes[i+3][j-3] == 2 && takenBoxes[i+4][j-4] == 2){
                    compY = (j-2) * 100;
                    compX = (i+2) * 100;
                    numD2 = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i+1][j-1] == 2 && takenBoxes[i+2][j-2] == 2 && takenBoxes[i+3][j-3] == 0 && takenBoxes[i+4][j-4] == 2){
                    compY = (j-3) * 100;
                    compX = (i+3) * 100;
                    numD2 = 13;
                    return true;
                }
                else if(takenBoxes[i][j] == 2 && takenBoxes[i+1][j-1] == 0 && takenBoxes[i+2][j-2] == 2 && takenBoxes[i+3][j-3] == 2 && takenBoxes[i+4][j-4] == 2){
                    compY = (j-1) * 100;
                    compX = (i+1) * 100;
                    numD2 = 13;
                    return true;
                }
            }
        }
        Dia2X:
        for(int i=0;i<=dimenY-5;i++) {
            for (int j = dimenY-1; j >= 4; j--) {
                if(takenBoxes[i][j] == 1 && takenBoxes[i+1][j - 1] == 1 && takenBoxes[i+2][j - 2] == 0 && takenBoxes[i+3][j-3] == 1 && takenBoxes[i+4][j-4] == 1){
                    compY = (j-2) * 100;
                    compX = (i+2) * 100;
                    numD2 = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i+1][j-1] == 1 && takenBoxes[i+2][j-2] == 1 && takenBoxes[i+3][j-3] == 0 && takenBoxes[i+4][j-4] == 1){
                    compY = (j-3) * 100;
                    compX = (i+3) * 100;
                    numD2 = 12;
                    return true;
                }
                else if(takenBoxes[i][j] == 1 && takenBoxes[i+1][j-1] == 0 && takenBoxes[i+2][j-2] == 1 && takenBoxes[i+3][j-3] == 1 && takenBoxes[i+4][j-4] == 1){
                    compY = (j+1) * 100;
                    compX = (i+1) * 100;
                    numD2 = 12;
                    return true;
                }
            }
        }
        return false;
    }
    public static int betweenTwoOnes(int pattern){
        //check for uncovered 2s
        if(pattern == 1 || pattern == 5) {
            hor:
            for (int i = 0; i <= dimenY - 3; i++) {
                for (int j = 0; j < dimenX; j++) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i + 1][j] == 0 && takenBoxes[i + 2][j] == 2) {
                        if ((i < 2 && (takenBoxes[3][j] == 1 || takenBoxes[4][j] == 1)) || (i > 5 && (takenBoxes[6][j] == 1 || takenBoxes[5][j] == 1))) ;
                        else if ((i > 0 && i < 6 && ((takenBoxes[i - 1][j] == 1 && takenBoxes[i + 4][j] == 1) || (takenBoxes[i - 1][j] == 1 && takenBoxes[i + 3][j] == 1))) || (i == 6 && (takenBoxes[i-1][j] == 1 && takenBoxes[i+3][j] == 1 ))) ;
                        else if(i>0 && i<7 && (!(takenBoxes[i-1][j]==0 && takenBoxes[i+3][j]==0) && !checkCovMiddle(3, 2)));
                        else {
                            compY = j * 100;
                            compX = (i + 1) * 100;
                            return 5;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i + 1][j] == 0 && takenBoxes[i + 2][j] == 1) {
                        if ((i < 2 && (takenBoxes[3][j] == 2 || takenBoxes[4][j] == 2)) || (i > 5 && (takenBoxes[6][j] == 2 || takenBoxes[5][j] == 2))) ;
                        else if ((i > 0 && i < 6 && ((takenBoxes[i - 1][j] == 2 && takenBoxes[i + 4][j] == 2) || (takenBoxes[i - 1][j] == 2 && takenBoxes[i + 3][j] == 2))) || (i == 6 && (takenBoxes[i-1][j] == 2 && takenBoxes[i+3][j] == 2 ))) ;
                        else if(i>0 && i<7 && (!(takenBoxes[i-1][j]==0 && takenBoxes[i+3][j]==0) && !checkCovMiddle(3, 1)));
                        else {
                            compY = j * 100;
                            compX = (i + 1) * 100;
                            return 3;
                        }
                    }
                }
            }
        }
        if(pattern == 2 || pattern == 5) {
            vert:
            for (int i = 0; i < dimenX; i++) {
                for (int j = 0; j <= dimenY - 3; j++) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i][j + 1] == 0 && takenBoxes[i][j + 2] == 2) {
                        if ((j < 2 && (takenBoxes[i][3] == 1 || takenBoxes[i][4] == 1)) || (j > 5 && (takenBoxes[i][6] == 1 || takenBoxes[i][5] == 1))) ;
                        else if ((j > 0 && j < 6 && ((takenBoxes[i][j - 1] == 1 && takenBoxes[i][j + 4] == 1) || (takenBoxes[i][j - 1] == 1 && takenBoxes[i][j + 3] == 1))) || (j == 6 && (takenBoxes[i][j - 1] == 1 && takenBoxes[i][j + 3] == 1 ))) ;
                        else if(j>0 && j<7 && (!(takenBoxes[i][j-1]==0 && takenBoxes[i][j+3]==0) && !checkCovMiddle(3, 2)));
                        else {
                            compY = (j + 1) * 100;
                            compX = i * 100;
                            return 5;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i][j + 1] == 0 && takenBoxes[i][j + 2] == 1) {
                        if ((j < 2 && (takenBoxes[i][3] == 2 || takenBoxes[i][4] == 2)) || (j > 5 && (takenBoxes[i][6] == 2 || takenBoxes[i][5] == 2))) ;
                        else if ((j > 0 && j < 6 && ((takenBoxes[i][j - 1] == 2 && takenBoxes[i][j + 4] == 2) || (takenBoxes[i][j - 1] == 2 && takenBoxes[i][j + 3] == 2))) || (j == 6 && (takenBoxes[i][j - 1] == 2 && takenBoxes[i][j + 3] == 2))) ;
                        else if(j>0 && j<7 && (!(takenBoxes[i][j-1]==0 && takenBoxes[i][j+3]==0) && !checkCovMiddle(3, 1)));
                        else {
                            compY = (j + 1) * 100;
                            compX = i * 100;
                            return 3;
                        }
                    }
                }
            }
        }
        if(pattern == 3 || pattern == 5){
            dia1:
            for (int i = 0; i < dimenX-3; i++) {
                for (int j = 0; j <= dimenY - 3; j++) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i+1][j + 1] == 0 && takenBoxes[i+2][j + 2] == 2) {
                        if(((i==dimenX-3 || i==dimenX-4) && (j==0 || j==1)) || ((i==0 || i==1) && (j==dimenY-3 || j==dimenY-4)));
                        else if ((j == 0 && (takenBoxes[i+3][j+3] == 1 || takenBoxes[i+4][j+4] == 1))  || (j == 1 && takenBoxes[i+3][j+3] == 1) || (j == 7 && (takenBoxes[i-1][j-1] == 1 || takenBoxes[i-2][j-2] == 1)) || (j == 6 && takenBoxes[i-1][j-1] == 1)) ;
                        else if ((i == 0 && (takenBoxes[i+3][j+3] == 1 || takenBoxes[i+4][j+4] == 1))  || (i == 1 && takenBoxes[i+3][j+3] == 1) || (i == 7 && (takenBoxes[i-1][j-1] == 1 || takenBoxes[i-2][j-2] == 1)) || (i == 6 && takenBoxes[i-1][j-1] == 1)) ;
                        else if ((j > 0 && j < 6 && i>0 && i<6 && ((takenBoxes[i-1][j - 1] == 1 && takenBoxes[i+4][j + 4] == 1) || (takenBoxes[i-1][j - 1] == 1 && takenBoxes[i+3][j + 3] == 1))) || (j == 6 && i ==6 && (takenBoxes[i - 1][j - 1] == 1 && takenBoxes[i + 3][j + 3] == 1 ))) ;
                        else if(j>0 && j<7 && i>0 && i<7 && (!(takenBoxes[i-1][j-1]==0 && takenBoxes[i+3][j+3]==0) && !checkCovMiddle(3, 2)));
                        else {
                            compY = (j + 1) * 100;
                            compX = (i+1) * 100;
                            return 5;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i+1][j + 1] == 0 && takenBoxes[i+2][j + 2] == 1) {
                        if(((i==dimenX-3 || i==dimenX-4) && (j==0 || j==1)) || ((i==0 || i==1) && (j==dimenY-3 || j==dimenY-4)));
                        else if ((j < 2 && (takenBoxes[i+3][j+3] == 2 || takenBoxes[i+4][j+4] == 2)) || (j > 5 && (takenBoxes[i-1][j-1] == 2 || takenBoxes[i-2][j-2] == 2))) ;
                        else if ((i < 2 && (takenBoxes[i+3][j+3] == 2 || takenBoxes[i+4][j+4] == 2)) || (i > 5 && (takenBoxes[i-1][j-1] == 2 || takenBoxes[i-2][j-2] == 2))) ;
                        else if ((j > 0 && j < 6 && i>0 && i<6 && ((takenBoxes[i-1][j - 1] == 2 && takenBoxes[i+4][j + 4] == 2) || (takenBoxes[i-1][j - 1] == 2 && takenBoxes[i+3][j + 3] == 2))) || (j == 6 && i == 6 && (takenBoxes[i - 1][j - 1] == 2 && takenBoxes[i + 3][j + 3] == 2))) ;
                        else if(j>0 && j<7 && i>0 && i<7 && (!(takenBoxes[i-1][j-1]==0 && takenBoxes[i+3][j+3]==0) && !checkCovMiddle(3, 1)));
                        else {
                            compY = (j + 1) * 100;
                            compX = (i+1) * 100;
                            return 3;
                        }
                    }
                }
            }
        }
        if(pattern == 4 || pattern == 5){
            dia2:
            for (int i = 0; i < dimenX-3; i++) {
                for (int j = dimenY-3; j >= 2; j--) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i+1][j - 1] == 0 && takenBoxes[i+2][j - 2] == 2) {
                        if(((i==0 || i==1) && (j==2 || j==3)) || ((i==dimenX-3 || i==dimenX-4) && (j==dimenY-1 || j==dimenY-2)));
                        else if ((j == 2 && (takenBoxes[i-1][j+1] == 1 || takenBoxes[i-2][j+2] == 1)) || (j==3 && takenBoxes[i-1][j+1] == 1) || (j > 7 && (takenBoxes[i+1][j-1] == 1 || takenBoxes[i+2][j-2] == 1 || takenBoxes[i+3][j-3] == 1)) || (j == 9 && takenBoxes[i+4][j-4] == 1)) ;
                        else if ((i == 2 && (takenBoxes[i-1][j+1] == 1 || takenBoxes[i-2][j+2] == 1)) || (i==3 && takenBoxes[i-1][j+1] == 1) || (i > 7 && (takenBoxes[i+1][j-1] == 1 || takenBoxes[i+2][j-2] == 1 || takenBoxes[i+3][j-3] == 1)) || (i == 9 && takenBoxes[i+4][j-4] == 1)) ;
                        else if ((j > 3 && j < 6 && i > 0 && i < 6 && (takenBoxes[i - 1][j + 1] == 1 && takenBoxes[i + 4][j - 4] == 1 || takenBoxes[i - 1][j + 1] == 1 && takenBoxes[i + 3][j - 3] == 1)) || (j < 9 && j>2 && i ==6 && (takenBoxes[i - 1][j + 1] == 1 && takenBoxes[i + 3][j - 3] == 1 ))) ;
                        else if(j<9 && j>2 && i>0 && i<7 && (!(takenBoxes[i-1][j+1]==0 && takenBoxes[i+3][j-3]==0) && !checkCovMiddle(3, 2)));
                        else {
                            compY = (j - 1) * 100;
                            compX = (i+1) * 100;
                            return 5;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i+1][j - 1] == 0 && takenBoxes[i+2][j - 2] == 1) {
                        if(((i==0 || i==1) && (j==2 || j==3)) || ((i==dimenX-3 || i==dimenX-4) && (j==dimenY-1 || j==dimenY-2)));
                        else if ((j == 2 && (takenBoxes[i-1][j+1] == 2 || takenBoxes[i-2][j+2] == 2)) || (j==3 && takenBoxes[i-1][j+1] == 2) || (j > 7 && (takenBoxes[i+1][j-1] == 2 || takenBoxes[i+2][j-2] == 2 || takenBoxes[i+3][j-3] == 2)) || (j == 9 && takenBoxes[i+4][j-4] == 2)) ;
                        else if ((i == 2 && (takenBoxes[i-1][j+1] == 2 || takenBoxes[i-2][j+2] == 2)) || (i==3 && takenBoxes[i-1][j+1] == 2) || (i > 7 && (takenBoxes[i+1][j-1] == 2 || takenBoxes[i+2][j-2] == 2 || takenBoxes[i+3][j-3] == 2)) || (i == 9 && takenBoxes[i+4][j-4] == 2)) ;
                        else if ((j > 3 && j < 6 && i > 0 && i < 6 && (takenBoxes[i - 1][j + 1] == 2 && takenBoxes[i + 4][j - 4] == 2 || takenBoxes[i - 1][j + 1] == 2 && takenBoxes[i + 3][j - 3] == 2)) || (j < 9 && j>2 && i == 6 && (takenBoxes[i - 1][j + 1] == 2 && takenBoxes[i + 3][j - 3] == 2 ))) ;
                        else if(j<9 && j>2 && i>0 && i<7 && (!(takenBoxes[i-1][j+1]==0 && takenBoxes[i+3][j-3]==0) && !checkCovMiddle(3, 1)));
                        else {
                            compY = (j - 1) * 100;
                            compX = (i+1) * 100;
                            return 3;
                        }
                    }
                }
            }
        }
        return -1;
    }
    public static int betweenOneATwo(int pattern){
        if(pattern == 1 || pattern == 5) {
            hor:
            for (int i = 0; i <= dimenY - 4; i++) {
                for (int j = 0; j < dimenX; j++) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i + 1][j] == 2 && takenBoxes[i + 2][j] == 0 && takenBoxes[i + 3][j] == 2) {
                        if ((i == 0 && takenBoxes[4][j] == 1) || (i == 6 && takenBoxes[5][j] == 1)) ;
                        else if (i > 0 && i < 6 && (takenBoxes[i - 1][j] == 1 && takenBoxes[i + 4][j] == 1)) ;
                        else if(i>0 && i<6 && (!(takenBoxes[i-1][j]==0 && takenBoxes[i+4][j]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = j * 100;
                            compX = (i + 2) * 100;
                            return 9;
                        }
                    } else if (takenBoxes[i][j] == 2 && takenBoxes[i + 1][j] == 0 && takenBoxes[i + 2][j] == 2 && takenBoxes[i + 3][j] == 2) {
                        if ((i == 0 && takenBoxes[4][j] == 1) || (i == 6 && takenBoxes[5][j] == 1)) ;
                        else if (i > 0 && i < 6 && (takenBoxes[i - 1][j] == 1 && takenBoxes[i + 4][j] == 1)) ;
                        else if(i>0 && i<6 && (!(takenBoxes[i-1][j]==0 && takenBoxes[i+4][j]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = j * 100;
                            compX = (i + 1) * 100;
                            return 9;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i + 1][j] == 1 && takenBoxes[i + 2][j] == 0 && takenBoxes[i + 3][j] == 1) {
                        if ((i == 0 && takenBoxes[4][j] == 2) || (i == 6 && takenBoxes[5][j] == 2)) ;
                        else if (i > 0 && i < 6 && (takenBoxes[i - 1][j] == 2 && takenBoxes[i + 4][j] == 2)) ;
                        else if(i>0 && i<6 && (!(takenBoxes[i-1][j]==0 && takenBoxes[i+4][j]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = j * 100;
                            compX = (i + 2) * 100;
                            return 7;
                        }
                    } else if (takenBoxes[i][j] == 1 && takenBoxes[i + 1][j] == 0 && takenBoxes[i + 2][j] == 1 && takenBoxes[i + 3][j] == 1) {
                        if ((i == 0 && takenBoxes[4][j] == 2) || (i == 6 && takenBoxes[5][j] == 2)) ;
                        else if (i > 0 && i < 6 && (takenBoxes[i - 1][j] == 2 && takenBoxes[i + 4][j] == 2)) ;
                        else if(i>0 && i<6 && (!(takenBoxes[i-1][j]==0 && takenBoxes[i+4][j]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = j * 100;
                            compX = (i + 1) * 100;
                            return 7;
                        }
                    }
                }
            }
        }
        if(pattern == 2 || pattern == 5) {
            vert:
            for (int i = 0; i < dimenY; i++) {
                for (int j = 0; j <= dimenX - 4; j++) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i][j+1] == 2 && takenBoxes[i][j+2] == 0 && takenBoxes[i][j+3] == 2) {
                        if ((j == 0 && takenBoxes[i][4] == 1) || (j == 6 && takenBoxes[i][5] == 1)) ;
                        else if (j > 0 && j < 6 && (takenBoxes[i][j-1] == 1 && takenBoxes[i][j+4] == 1)) ;
                        else if(j>0 && j<6 && (!(takenBoxes[i][j-1]==0 && takenBoxes[i][j+4]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = (j+2) * 100;
                            compX = i * 100;
                            return 9;
                        }
                    } else if (takenBoxes[i][j] == 2 && takenBoxes[i][j+1] == 0 && takenBoxes[i][j+2] == 2 && takenBoxes[i][j+3] == 2) {
                        if ((j == 0 && takenBoxes[i][4] == 1) || (j == 6 && takenBoxes[i][5] == 1)) ;
                        else if (j > 0 && j < 6 && (takenBoxes[i][j-1] == 1 && takenBoxes[i][j+4] == 1)) ;
                        else if(j>0 && j<6 && (!(takenBoxes[i][j-1]==0 && takenBoxes[i][j+4]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = (j+1) * 100;
                            compX = i * 100;
                            return 9;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i][j+1] == 1 && takenBoxes[i][j+2] == 0 && takenBoxes[i][j+3] == 1) {
                        if ((j == 0 && takenBoxes[i][4] == 2) || (j == 6 && takenBoxes[i][5] == 2)) ;
                        else if (j > 0 && j < 6 && (takenBoxes[i][j-1] == 2 && takenBoxes[i][j+4] == 2)) ;
                        else if(j>0 && j<6 && (!(takenBoxes[i][j-1]==0 && takenBoxes[i][j+4]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = (j+2) * 100;
                            compX = i * 100;
                            return 7;
                        }
                    } else if (takenBoxes[i][j] == 1 && takenBoxes[i][j+1] == 0 && takenBoxes[i][j+2] == 1 && takenBoxes[i][j+3] == 1) {
                        if ((j == 0 && takenBoxes[i][4] == 2) || (j == 6 && takenBoxes[i][5] == 2)) ;
                        else if (j > 0 && j < 6 && (takenBoxes[i][j-1] == 2 && takenBoxes[i][j+4] == 2)) ;
                        else if(j>0 && j<6 && (!(takenBoxes[i][j-1]==0 && takenBoxes[i][j+4]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = (j+1) * 100;
                            compX = i * 100;
                            return 7;
                        }
                    }
                }
            }
        }
        if(pattern == 3 || pattern == 5){
            for (int i = 0; i <= dimenY - 4; i++) {
                for (int j = 0; j <= dimenX - 4; j++) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i+1][j+1] == 2 && takenBoxes[i+2][j+2] == 0 && takenBoxes[i+3][j+3] == 2 && !((i==0 && j==6) || (i==6 && j==0))) {
                        if ((j == 0 && takenBoxes[i+4][j+4] == 1) || (j == 6 && takenBoxes[i-1][j-1] == 1)) ;
                        else if((i == 0 && takenBoxes[i+4][j+4] == 1) || (i == 6 && takenBoxes[i-1][j-1] == 1));
                        else if (j > 0 && j < 6 && i<6 && i>0 && (takenBoxes[i-1][j-1] == 1 && takenBoxes[i+4][j+4] == 1)) ;
                        else if(j>0 && j<6 && i>0 && i<6 && (!(takenBoxes[i-1][j-1]==0 && takenBoxes[i+4][j+4]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = (j+2) * 100;
                            compX = (i+2) * 100;
                            return 9;
                        }
                    } else if (takenBoxes[i][j] == 2 && takenBoxes[i+1][j+1] == 0 && takenBoxes[i+2][j+2] == 2 && takenBoxes[i+3][j+3] == 2 && !((i==0 && j==6) || (i==6 && j==0))) {
                        if ((j == 0 && takenBoxes[i+4][j+4] == 1) || (j == 6 && takenBoxes[i-1][j-1] == 1)) ;
                        else if((i == 0 && takenBoxes[i+4][j+4] == 1) || (i == 6 && takenBoxes[i-1][j-1] == 1));
                        else if (j > 0 && j < 6 && i<6 && i>0 && (takenBoxes[i-1][j-1] == 1 && takenBoxes[i+4][j+4] == 1)) ;
                        else if(j>0 && j<6 && i>0 && i<6 && (!(takenBoxes[i-1][j-1]==0 && takenBoxes[i+4][j+4]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = (j+1) * 100;
                            compX = (i+1) * 100;
                            return 9;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i+1][j+1] == 1 && takenBoxes[i+2][j+2] == 0 && takenBoxes[i+3][j+3] == 1&& !((i==0 && j==6) || (i==6 && j==0))) {
                        if ((j == 0 && takenBoxes[i+4][j+4] == 2) || (j == 6 && takenBoxes[i-1][j-1] == 2)) ;
                        else if((i == 0 && takenBoxes[i+4][j+4] == 2) || (i == 6 && takenBoxes[i-1][j-1] == 2));
                        else if (j > 0 && j < 6 && i<6 && i>0 && (takenBoxes[i-1][j-1] == 2 && takenBoxes[i+4][j+4] == 2)) ;
                        else if(j>0 && j<6 && i>0 && i<6 && (!(takenBoxes[i-1][j-1]==0 && takenBoxes[i+4][j+4]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = (j+2) * 100;
                            compX = (i+2) * 100;
                            return 7;
                        }
                    } else if (takenBoxes[i][j] == 1 && takenBoxes[i+1][j+1] == 0 && takenBoxes[i+2][j+2] == 1 && takenBoxes[i+3][j+3] == 1 && !((i==0 && j==6) || (i==6 && j==0))) {
                        if ((j == 0 && takenBoxes[i+4][j+4] == 2) || (j == 6 && takenBoxes[i-1][j-1] == 2)) ;
                        else if((i == 0 && takenBoxes[i+4][j+4] == 2) || (i == 6 && takenBoxes[i-1][j-1] == 2));
                        else if (j > 0 && j < 6 && i<6 && i>0 && (takenBoxes[i-1][j-1] == 2 && takenBoxes[i+4][j+4] == 2)) ;
                        else if(j>0 && j<6 && i>0 && i<6 && (!(takenBoxes[i-1][j-1]==0 && takenBoxes[i+4][j+4]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = (j+1) * 100;
                            compX = (i+1) * 100;
                            return 7;
                        }
                    }
                }
            }
        }
        if(pattern == 4 || pattern == 5){
            for (int i = 0; i <= dimenY - 4; i++) {
                for (int j = dimenX-1; j >= 3; j--) {
                    if (takenBoxes[i][j] == 2 && takenBoxes[i+1][j-1] == 2 && takenBoxes[i+2][j-2] == 0 && takenBoxes[i+3][j-3] == 2 && !((i==0 && j==3) || (i==6 && j==9))) {
                        if ((j == 9 && takenBoxes[i+4][j-4] == 1) || (j == 3 && takenBoxes[i-1][j+1] == 1)) ;
                        else if ((i == 0 && takenBoxes[i+4][j-4] == 1) || (i == 6 && takenBoxes[i-1][j+1] == 1)) ;
                        else if (j > 3 && j < 9 && i < 6 && i > 0 && takenBoxes[i - 1][j + 1] == 1 && takenBoxes[i + 4][j - 4] == 1) ;
                        else if(j<9 && j>3 && i>0 && i<6 && (!(takenBoxes[i-1][j+1]==0 && takenBoxes[i+4][j-4]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = (j-2) * 100;
                            compX = (i+2) * 100;
                            return 9;
                        }
                    } else if (takenBoxes[i][j] == 2 && takenBoxes[i+1][j-1] == 0 && takenBoxes[i+2][j-2] == 2 && takenBoxes[i+3][j-3] == 2 && !((i==0 && j==3) || (i==6 && j==9))) {
                        if ((j == 9 && takenBoxes[i+4][j-4] == 1) || (j == 3 && takenBoxes[i-1][j+1] == 1)) ;
                        else if ((i == 0 && takenBoxes[i+4][j-4] == 1) || (i == 6 && takenBoxes[i-1][j+1] == 1)) ;
                        else if (j > 3 && j < 9 && i < 6 && i > 0 && takenBoxes[i - 1][j + 1] == 1 && takenBoxes[i + 4][j - 4] == 1) ;
                        else if(j<9 && j>3 && i>0 && i<6 && (!(takenBoxes[i-1][j+1]==0 && takenBoxes[i+4][j-4]==0) && !checkCovMiddle(4, 2)));
                        else {
                            compY = (j-1) * 100;
                            compX = (i+1) * 100;
                            return 9;
                        }
                    }
                    if (takenBoxes[i][j] == 1 && takenBoxes[i+1][j-1] == 1 && takenBoxes[i+2][j-2] == 0 && takenBoxes[i+3][j-3] == 1&& !((i==0 && j==3) || (i==6 && j==9))) {
                        if ((j == 9 && takenBoxes[i+4][j-4] == 2) || (j == 3 && takenBoxes[i-1][j+1] == 2)) ;
                        else if ((i == 0 && takenBoxes[i+4][j-4] == 2) || (i == 6 && takenBoxes[i-1][j+1] == 2)) ;
                        else if (j > 3 && j < 9 && i < 6 && i > 0 && takenBoxes[i - 1][j + 1] == 2 && takenBoxes[i + 4][j - 4] == 2) ;
                        else if(j<9 && j>3 && i>0 && i<6 && (!(takenBoxes[i-1][j+1]==0 && takenBoxes[i+4][j-4]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = (j-2) * 100;
                            compX = (i+2) * 100;
                            return 7;
                        }
                    } else if (takenBoxes[i][j] == 1 && takenBoxes[i+1][j-1] == 0 && takenBoxes[i+2][j-2] == 1 && takenBoxes[i+3][j-3] == 1 && !((i==0 && j==3) || (i==6 && j==9))) {
                        if ((j == 9 && takenBoxes[i+4][j-4] == 2) || (j == 3 && takenBoxes[i-1][j+1] == 2)) ;
                        else if ((i == 0 && takenBoxes[i+4][j-4] == 2) || (i == 6 && takenBoxes[i-1][j+1] == 2)) ;
                        else if (j > 3 && j < 9 && i < 6 && i > 0 && takenBoxes[i - 1][j + 1] == 2 && takenBoxes[i + 4][j - 4] == 2) ;
                        else if(j<9 && j>3 && i>0 && i<6 && (!(takenBoxes[i-1][j+1]==0 && takenBoxes[i+4][j-4]==0) && !checkCovMiddle(4, 1)));
                        else {
                            compY = (j-1) * 100;
                            compX = (i+1) * 100;
                            return 7;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
