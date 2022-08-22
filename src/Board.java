import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Board implements Cloneable {
    private int[][] board;
    ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    public Board(int[][] sudoku) {
        this.board = sudoku;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getValue(int x, int y) {
        return board[x][y];
    }

    public void setValue(int x, int y, int value) {
        board[x][y] = value;
    }

    public boolean checkGrid() {
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                if (getValue(x,y) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showBoard() {
        for(int index = 0; index < 81; index++) {
            if(index % 27 == 0 && index != 0) {
                System.out.print("-  -  - | -  -  - | -  -  -\n");
            }
            if(index % 3 == 2 && index % 9 != 8) {
                System.out.print(board[index] + " | ");
            } else System.out.print(board[index] + "  ");
            if(index % 9 == 8) System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean checkPlacement(int x, int y) {
        int ox = x;
        int oy = y;
        int current = getValue(x, y);

        //Row
        /*for(int x = 0; x < 9; x++) {
            //System.out.println("Checking Row: " + getValue(x, oy) + " at " + getIndex(x, oy) + "\n");
            if(x != ox && getValue(x, oy) == current) {
                return false;
            }
        }

        //Column
        for(int y = 0; y < 9; y++) {
            //System.out.println("Checking Column: " + getValue(ox, y) + " at " + getIndex(ox, y) + "\n");
            if(y != oy && getValue(ox, y) == current) {
                return false;
            }
        }

        //Square
        int topX = ox - (ox % 3);
        int topY = oy - (oy % 3);

        for(int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                //System.out.println("Checking Square: " + getValue(topX + x, topY + y) + " at " + getIndex(topX + x, topY + y) + "\n");
                if((topX + x != ox) && (topY + y != oy)) {
                    if(getValue(topX + x, topY + y) == current) {
                        return false;
                    }
                }
            }
        }
        return true;
    }*/

    public boolean createPuzzle() {
        for (int _x = 0; _x < 9; _x++) {
            for (int _y = 0; _y < 9; _y++) {
                if (getValue(_x, _y) == 0) {
                    Collections.shuffle(numberList);

                    for (int number : numberList) {
                        setValue(_x, _y, number);
                        if (checkPlacement(_x, _y)) {
                            if (checkGrid()) {
                                return true;
                            } else {
                                if (createPuzzle()) {
                                    return true;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        setValue(x, y, 0);
        return false;
    }

    public static Board generate(int difficulty) throws CloneNotSupportedException {
        int randomPlacement;
        int temp;
        int currentAttempts;
        Board puzzle = new Board(new int[][] {});

        puzzle.createPuzzle();

        while(true) {
            randomPlacement = (int) (Math.random() * 81);

            if(puzzle.getValue(randomPlacement) != 0) {
                temp = puzzle.getValue(randomPlacement);

                puzzle.setValue(randomPlacement, 0);
                Board puzzleCopy = (Board) puzzle.clone();

                currentAttempts = Solver.solve(puzzleCopy, difficulty);

                if (currentAttempts >= difficulty) {
                    puzzle.setValue(randomPlacement, temp);
                    break;
                }
            }
        }
        return puzzle;
    }
/*
    public static void example() {
        Board board = new Board(new int[][] {

        });

        Instant start = Instant.now();

        Solver.solve(board, 10);

        Instant finish = Instant.now();

        double timeElapsed = Duration.between(start, finish).toNanos();

        board.showBoard();
        System.out.println("Solution took: " + (timeElapsed / 1000000000) + "s");
    }*/
}
