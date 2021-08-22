public class Board {
    static int[] board;
    static final int width = 9;

    public Board(int[] sudoku) {
        if(sudoku.length == 81) {
            board = sudoku;
        } else System.out.println("Invalid Board Data!");
    }

    public int getIndex(int x, int y) {
        return (y * width) + x;
    }

    public int getValue(int x, int y) {
        return board[getIndex(x, y)];
    }

    public void setValue(int index, int value) {
        board[index] = value;
    }

    public int getValue(int index) {
        return board[index];
    }

    public int getX(int index) {
        return index % 9;
    }

    public int getY(int index) {
        return index / 9;
    }

    public void showBoard() {
        for(int index = 0; index < 81; index++) {
            System.out.print(board[index] + "  ");
            if(index % 9 == 8) System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean checkPlacement(int index) {
        int ox = index % 9;
        int oy = index / 9;
        int current = getValue(index);

        //Row
        for(int x = 0; x < 9; x++) {
            //System.out.println("Checking Row: " + getValue(row + x) + " at " + (row + x) + "\n");
            if(x != ox && getValue(x, oy) == current) {
                return false;
            }
        }

        //Column
        for(int y = 1; y < 9; y++) {
            //System.out.println("Checking Column: " + getValue(column + (9 * y)) + " at " + (column + (9 * y)) + "\n");
            if(y != oy && getValue(ox, y) == current) {
                return false;
            }
        }

        //Square
        int topX = ox - (ox % 3);
        int topY = oy - (oy % 3);

        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                //System.out.println("Checking Square: " + getValue(topX + x, topY + y) + " at (" + (topX + x) + ", " + (topY + y) + ")\n");
                if((topX + x != ox) && (topY + y != oy)) {
                    if(getValue(topX + x, topY + y) == current) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
