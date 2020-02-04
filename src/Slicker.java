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
        final List<int[]> moves = new ArrayList<>();
        switch (board.getBoard()[x][y].getPiece()) {
            case PAWN:
                final int direction = isWhite ? 1 : -1;
                if (isValidSquare(x, y + direction, isWhite)) moves.add(new int[]{x, y + direction});
                if (isValidSquare(x, y + 2 * direction, isWhite)) moves.add(new int[]{x, y + 2 * direction});
                break;

            case KNIGHT:
                for (int x0 = -2; x0 <= 2; x0++) {
                    if (x0 == 0) continue;
                    if (isValidSquare(x + x0, y + 3 - Math.abs(x0), isWhite)) moves.add(new int[]{x + x0, y + 3 - Math.abs(x0)});
                    if (isValidSquare(x + x0, y - 3 + Math.abs(x0), isWhite)) moves.add(new int[]{x + x0, y - 3 + Math.abs(x0)});
                }

                break;

            case BISHOP:
                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x + i, y + i, isWhite)) break;
                    moves.add(new int[]{x + i, y + i});
                }

                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x + i, y - i, isWhite)) break;
                    moves.add(new int[]{x + i, y - i});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x + i, y + i, isWhite)) break;
                    moves.add(new int[]{x + i, y + i});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x + i, y - i, isWhite)) break;
                    moves.add(new int[]{x + i, y - i});
                }

                break;

            case ROOK:
                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x + i, y, isWhite)) break;
                    moves.add(new int[]{x + i, y});
                }

                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x, y + i, isWhite)) break;
                    moves.add(new int[]{x, y + i});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x + i, y, isWhite)) break;
                    moves.add(new int[]{x + i, y});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x, y + i, isWhite)) break;
                    moves.add(new int[]{x, y + i});
                }

                break;

            case QUEEN:
                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x + i, y, isWhite)) break;
                    moves.add(new int[]{x + i, y});
                }

                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x, y + i, isWhite)) break;
                    moves.add(new int[]{x, y + i});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x + i, y, isWhite)) break;
                    moves.add(new int[]{x + i, y});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x, y + i, isWhite)) break;
                    moves.add(new int[]{x, y + i});
                }

                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x + i, y + i, isWhite)) break;
                    moves.add(new int[]{x + i, y + i});
                }

                for (int i = -1; i >= -7; i--) {
                    if (!isValidSquare(x + i, y - i, isWhite)) break;
                    moves.add(new int[]{x + i, y - i});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x + i, y + i, isWhite)) break;
                    moves.add(new int[]{x + i, y + i});
                }

                for (int i = 1; i <= 7; i++) {
                    if (!isValidSquare(x + i, y - i, isWhite)) break;
                    moves.add(new int[]{x + i, y - i});
                }

                break;

            case KING:
                for (int x0 = -1; x0 <= 1; x0++) {
                    for (int y0 = -1; y0 <= 1; y0++) {
                        if (x0 == 0 && y0 == 0) continue;
                        if (isValidSquare(x + x0, y + y0, isWhite)) moves.add(new int[]{x + x0, y + y0});
                    }
                }

                break;
        }

        return moves;
    }

    private static boolean isValidSquare(final int x, final int y, final boolean isWhite) {
        return 0 <= x && x < 8 && 0 <= y && y < 8 && board.getBoard()[x][y].getColor() != Boolean.valueOf(isWhite);
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
