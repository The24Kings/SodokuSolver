import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner scan = new Scanner(System.in);
        Board puzzle;
        int difficulty = 5;
        boolean running = true;

        while(running) {
            puzzle = null;

            System.out.print("Would you like to solve a puzzle?\n> ");
            String ans = scan.nextLine();

            if (ans.equalsIgnoreCase("y")) {
                System.out.print("How Difficult do you want it to be?\n> ");
                difficulty = scan.nextInt();

                puzzle = Board.generate(difficulty);
                puzzle.showBoard();
            } else {
                running = false;
            }

            if(puzzle != null) {
                System.out.print("Would you like to see the solution?\n> ");
                scan.nextLine(); //Consume previous 'nextInt()' because scanner class dumb
                ans = scan.nextLine();

                if (ans.equalsIgnoreCase("y")) {
                    Solver.solve(puzzle, difficulty);
                    puzzle.showBoard();
                }
            }
        }
    }
}
