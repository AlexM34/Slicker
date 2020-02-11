import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        validMoves(true);
    }

    private static Map<int[], List<int[]>> validMoves(final boolean isWhite) {
        final Map<int[], List<int[]>> moves = new HashMap<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                final int[] source = new int[]{x, y};
                if (board.getBoard()[x][y].getColor() == Boolean.valueOf(isWhite)) {
                    for (final int[] destination : validPieceMoves(x, y)) {
                        if (moves.containsKey(source)) moves.get(source).add(destination);
                        else moves.put(source, new ArrayList<>(Collections.singletonList(destination)));
                    }
                }

                if (moves.containsKey(source)) {
                    System.out.print(notation(source) + " -> ");
                    for (final int[] destination : moves.get(source)) {
                        System.out.print(notation(destination) + " ");
                    }

                    System.out.println();
                }
            }
        }

        return moves;
    }

    private static List<int[]> validPieceMoves(final int x, final int y) {
        final boolean isWhite = board.getBoard()[x][y].getColor();
        return board.getBoard()[x][y].getPiece().getValidSquares(board.getBoard(), x, y, isWhite);
    }

    private static String notation(final int[] square) {
        String notation = String.valueOf((char) (square[0] + 'a'));
        notation += (char) (square[1] + '1');
        return notation;
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
