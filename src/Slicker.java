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
        getValidMoves(true);
    }

    private static Map<Coordinates, List<Coordinates>> getValidMoves(final boolean isWhite) {
        final Map<Coordinates, List<Coordinates>> moves = new HashMap<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                final Coordinates source = new Coordinates(x, y);
                if (board.getBoard()[x][y].getColor() == Boolean.valueOf(isWhite)) {
                    for (final Coordinates destination : validPieceMoves(source)) {
                        if (moves.containsKey(source)) moves.get(source).add(destination);
                        else moves.put(source, new ArrayList<>(Collections.singletonList(destination)));
                    }
                }

                if (moves.containsKey(source)) {
                    System.out.print(notation(source) + " -> ");
                    for (final Coordinates destination : moves.get(source)) {
                        System.out.print(notation(destination) + " ");
                    }

                    System.out.println();
                }
            }
        }

        return moves;
    }

    private static List<Coordinates> validPieceMoves(final Coordinates source) {
        final Square square = board.getSquare(source);
        return square.getPiece().getValidSquares(board, source, square.getColor());
    }

    private static String notation(final Coordinates coordinates) {
        String notation = String.valueOf((char) (coordinates.getX() + 'a'));
        notation += (char) (coordinates.getY() + '1');
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
