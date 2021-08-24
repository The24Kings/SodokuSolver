import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*Board puzzle = new Board(new int[] {
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
        });

        puzzle.fillGrid();
        puzzle.showBoard();*/

        Scanner scan = new Scanner(System.in);
        Board puzzle;
        int difficulty = 5;
        boolean running = true;

        while(running) {
            puzzle = null;

            System.out.println("Would you like to solve a puzzle?");
            String ans = scan.nextLine();

            if (ans.equalsIgnoreCase("y")) {
                System.out.println("How Difficult do you want it to be?");
                difficulty = scan.nextInt();

                puzzle = Board.generate(difficulty);
                puzzle.showBoard();
            } else {
                running = false;
            }

            if(puzzle != null) {
                System.out.println("Would you like to see the solution?");
                scan.nextLine();
                ans = scan.nextLine();

                if (ans.equalsIgnoreCase("y")) {
                    Solver.solve(puzzle, difficulty);
                    puzzle.showBoard();
                }
            }
        }

        //Board.example();
    }
}
