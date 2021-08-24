import java.util.HashSet;

public class Solver {
    public static int solve(Board board, int numOfAttempts) {
        int index = 0;
        boolean solved = false;
        int attempts = 0;

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
                //Check if no solution was found and reverse
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
                            //Check if another solution is finished, else continue searching
                            if (board.getValue(r) == 10) {
                                attempts++;
                                board.setValue(index, 0);
                                if(attempts > numOfAttempts) break;

                            } else break;
                        }
                    }

                    if(attempts > numOfAttempts) {
                        break;
                    }
                }
            }
            index++;

            if(index > 80) {
                solved = true;
            }
        }
        return attempts;
    }
}
