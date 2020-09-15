import java.util.Scanner;

public class gameV2 {
    public static byte[][] fields = new byte[6][7];
    public static byte activePlayer = 1;

    public static void main(String[] args) {
        byte winner = 0;
        System.out.println("Welcome zu 4 wins");
        while (winner == 0) {
            turn();
            print();

            if (checkWin()) {
                winner = activePlayer;
            }

            if (activePlayer == 1) {
                activePlayer = 2;
            } else {
                activePlayer = 1;
            }
        }
        System.out.println("Congratulations, Player " + winner);

    }

    public static void print() { //druckt das Spielfeld aus
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("- - - - - - -");
        byte c = 6;
        for (int i = 5; i >= 0; --i) {
            for (int j = 0; j < fields[i].length; ++j) {
                int x = fields[i][j];
                System.out.print(x == 0 ? "  " : x == 1 ? "X " : "O ");
            }
            System.out.println(" [" + c + "]");
            c--;
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    public static void turn() {
        System.out.println(activePlayer == 1 ? "Player 1's turn" : activePlayer == 2 ? "Player 2's turn" : "ERROR");
        byte column = askForColumn(); //Gibt wert zwischen 1 und 7 zurück
        byte row = checkColumn(column);
        fields[row][column] = activePlayer;
    }

    public static byte askForColumn() { //Benutzer muss einen wert zwischen 1 und 7 eingeben
        byte column;
        Scanner sc = new Scanner(System.in);
        System.out.print("Stone in Column: ");
        column = sc.nextByte();
        while (column > 7 | column < 1) {
            System.out.println("invalid input");
            System.out.println("provide a value between 1 and 7");
            System.out.print("Stone in column: ");
            column = sc.nextByte();
        }
        column--;
        return column;
    }

    public static byte checkColumn(byte columnToCheck) { //testet, wie viele Steine in der Spalte sid
        byte numOfStones = 99;
        byte i = 0;
        while (numOfStones == 99) {
            if (fields[i][columnToCheck] == 0) {
                numOfStones = i;
            } else if (i == 7) {
                System.out.println("ERROR");
                System.exit(1);
            }
            i++;
        }
        return numOfStones;
    }

    public static boolean checkWin() {
        boolean foundWin = false;
        byte checkedRow = 0;
        byte checkedColumn = 0;

        while (checkedRow <= 5) {
            if (checkWinRow(checkedRow)) {
                foundWin = true;
                break;
            } else {
                checkedRow++;
            }
        }
        while (checkedColumn <= 6) {
            if (checkWinColumn(checkedColumn)) {
                foundWin = true;
                break;
            } else {
                checkedColumn++;
            }
        }
        if (checkWinDiagonal()) {
            foundWin = true;
        }
        return foundWin;
    }

    public static boolean checkWinRow(byte row) {
        boolean foundWin = false;
        byte i = 0;
        while (i < 3) {
            if (fields[row][i] == activePlayer && fields[row][(i + 1)] == activePlayer && fields[row][(i + 2)] == activePlayer && fields[row][(i + 3)] == activePlayer) {
                foundWin = true;
                break;
            }
            i++;
        }
        return foundWin;
    }

    public static boolean checkWinColumn(byte column) {
        boolean foundWin = false;
        byte i = 0;
        while (i < 2) {
            if (fields[i][column] == activePlayer && fields[(i + 1)][column] == activePlayer && fields[(i + 2)][column] == activePlayer && fields[(i + 3)][column] == activePlayer) {
                foundWin = true;
                break;
            }
            i++;
        }
        return foundWin;
    }

    public static boolean checkWinDiagonal() {
        boolean foundWin = false;

        for (byte i = 5; i >= 3; --i) {
            for (byte j = 0; j <= 2; ++j) {
                if (fields[i][j] == activePlayer && fields[i - 1][j + 1] == activePlayer && fields[i - 2][j + 2] == activePlayer && fields[i - 3][j + 3] == activePlayer) {
                    foundWin = true;
                    break;
                }
            }
        }
        for (byte i = 5; i >= 3; --i) {
            for (byte j = 3; j <= 6; ++j) {
                if (foundWin | fields[i][j] == activePlayer && fields[i - 1][j - 1] == activePlayer && fields[i - 2][j - 2] == activePlayer && fields[i - 3][j - 3] == activePlayer) {
                    foundWin = true;
                    break;

                }
            }
        }
        return foundWin;
    }
}