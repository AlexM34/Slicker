import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Slicker {
    private static Scanner scanner = new Scanner(System.in);
    private static int USER;
    private static Board board;
    private static boolean isWhite;

    public static void main(final String[] args) {
        initialise();
        System.out.println("Choose White(w) or Black(b)");
        String c = scanner.nextLine();
        while(!c.equals("w") && !c.equals("b")) c = scanner.nextLine();
        USER = c.equals("w") ? 0 : 1;
        System.out.println(USER);
        getValidMoves(true);

        startGame();
    }

    private static void startGame() {
        isWhite = true;
        while(true) {
            if (isUserMove()) userMove();
            else computerMove();
        }
    }

    private static boolean isUserMove() {
        return (USER == 0) == isWhite;
    }

    private static void userMove() {
        final Map<Coordinates, List<Coordinates>> validMoves = getValidMoves(isWhite);
        while(true) {
            final String input = scanner.nextLine();
            final List<Coordinates> move = parseMove(input);

            if (isAllowed(move, validMoves)) {
                play(move);
                break;
            }
        }
    }

    private static void computerMove() {
        final Map<Coordinates, List<Coordinates>> validMoves = getValidMoves(isWhite);
        final Coordinates[] sources = validMoves.keySet().toArray(Coordinates[]::new);
        final Coordinates source = sources[new Random().nextInt(sources.length)];
        final Coordinates[] destinations = validMoves.get(source).toArray(Coordinates[]::new);
        final Coordinates destination = destinations[new Random().nextInt(destinations.length)];

        play(Arrays.asList(source, destination));
    }

    private static boolean isAllowed(final List<Coordinates> move,
                                     final Map<Coordinates, List<Coordinates>> validMoves) {

        final List<Coordinates> validDestinations = validMoves.get(move.get(0));

        return validDestinations != null && validDestinations.contains(move.get(1));
    }

    private static void play(final List<Coordinates> move) {
        Board.play(move);
        isWhite = !isWhite;
        printBoard();
    }

    private static List<Coordinates> parseMove(final String move) {
        final Coordinates source = transformCoordinates(move.substring(0, 2));
        final Coordinates destination = transformCoordinates(move.substring(2, 4));
        return Arrays.asList(source, destination);
    }

    private static Coordinates transformCoordinates(final String s) {
        return new Coordinates(s.charAt(0) - 'a', s.charAt(1) - '1');
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
        printBoard();
    }

    private static void printBoard() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                System.out.print(board.getBoard()[x][y].printValue());
            }

            System.out.println();
        }
    }
}
