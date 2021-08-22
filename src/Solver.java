import java.util.HashMap;
import java.util.HashSet;

public class Solver {
    public static void solve(Board board) {
        int index = 0;
        boolean solved = false;

        HashSet<Integer> known = new HashSet<>();
        for(int i = 0; i < 81; i++) {
            if(board.getValue(i) != 0) {
                known.add(i);
            }
        }

        while(!solved) {
            if (!known.contains(index)) {
                for (int number = 1; number < 11; number++) {
                    board.setValue(index, number);
                    //If placement is correct go to next box
                    if (board.checkPlacement(index)) {
                        break;
                    }
                }
                if(board.getValue(index) == 10) {
                    board.setValue(index, 0); //No solution is found, set to 0
                    for(int r = index - 1; r > 0; r-- ) {
                        if(!known.contains(r)) {
                            for (int number = board.getValue(r) + 1; number < 11; number++) {
                                board.setValue(r, number);
                                if (board.checkPlacement(r)) {
                                    index = r;
                                    break;
                                }
                            }
                            if (board.getValue(r) == 10) {
                                board.setValue(index, 0);
                            } else break;
                        }
                    }
                }
            }

            /*Debugging
            System.out.print("(" + board.getX(index) + ", " + board.getY(index) + ")\t");
            System.out.println(board.getValue(index));
            Board.showBoard(board);
            */

            index++;

            if(index > 80) {
                solved = true;
            }
        }
    }
}
