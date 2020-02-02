import java.util.Scanner;

public class Slicker {
    private static Scanner scanner = new Scanner(System.in);
    private static int USER;
    private static Board board;

    public static void main(final String[] args) {
        initialise();
        System.out.println("Choose White(w) or Black(b)");
        String c = scanner.nextLine();
        while(!c.equals("w") && !c.equals("b")) c = scanner.nextLine();
        USER = c.equals("w") ? 0 : 1;
        System.out.println(USER);
    }

    private static void initialise() {
        board = new Board();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                System.out.print(board.getBoard()[x][y].printValue());
            }

            System.out.println();
        }
    }
}
