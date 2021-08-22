public class main {
    public static void main(String[] args) {
        Board board = new Board(new int[] {
            0,0,6,1,8,4,5,9,0,
            5,9,0,6,0,2,8,0,4,
            4,8,0,9,5,7,2,0,1,
            0,0,0,0,1,0,0,8,6,
            1,0,2,8,9,0,4,5,7,
            0,3,0,0,0,6,0,0,0,
            0,2,0,7,0,9,0,1,0,
            6,0,0,0,0,0,9,4,0,
            0,0,0,5,6,8,0,0,2
        });

        Solver.solve(board);

        for(int index = 0; index < 81; index++) {
            System.out.print(board.getValue(index) + "  ");
            if(index % 9 == 8) System.out.print("\n");
        }
    }
}
