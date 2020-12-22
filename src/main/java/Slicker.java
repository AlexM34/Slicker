import setup.Board;
import setup.Color;
import setup.Coordinates;
import setup.Piece;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Slicker {

    private static final PrintStream STREAM = new PrintStream(new FileOutputStream(FileDescriptor.out));
    private static final Scanner SCANNER = new Scanner(System.in);

    private static Board board;
    private static Color user;
    private static Color toMove;
    private static boolean inProgress;

    public static void main(final String[] args) {
        initialise();
        STREAM.println("Choose White(w) or Black(b)");
        String c = SCANNER.nextLine();
        while(!c.equals("w") && !c.equals("b")) c = SCANNER.nextLine();
        user = c.equals("w") ? Color.WHITE : Color.BLACK;
        STREAM.println(user);

        playGame();
    }

    private static void playGame() {
        while (inProgress) {
            if (isUserMove()) userMove();
            else randomMove();
        }
    }

    private static boolean isUserMove() {
        return user.equals(toMove);
    }

    private static void userMove() {
        final Map<Coordinates, List<Coordinates>> validMoves = getValidMoves(toMove);
        if (validMoves.isEmpty()) {
            gameOver();
            return;
        }

        while(true) {
            final String input = SCANNER.nextLine();
            if (input.length() != 4) continue;
            final List<Coordinates> move = parseMove(input);

            if (isAllowed(move, validMoves)) {
                play(move);
                break;
            }
        }
    }

    private static void randomMove() {
        final Map<Coordinates, List<Coordinates>> validMoves = getValidMoves(toMove);
        if (validMoves.isEmpty()) {
            gameOver();
            return;
        }

        final Coordinates[] sources = validMoves.keySet().toArray(Coordinates[]::new);
        final Coordinates source = sources[new Random().nextInt(sources.length)];
        final Coordinates[] destinations = validMoves.get(source).toArray(Coordinates[]::new);
        final Coordinates destination = destinations[new Random().nextInt(destinations.length)];

        play(Arrays.asList(source, destination));
    }

    private static void gameOver() {
        inProgress = false;

        if (!user.equals(toMove)) STREAM.println("YOU WIN!");
        else STREAM.println("YOU LOSE!");

        board.printMoves(STREAM);
    }

    private static boolean isAllowed(final List<Coordinates> move,
                                     final Map<Coordinates, List<Coordinates>> validMoves) {

        final List<Coordinates> validDestinations = validMoves.getOrDefault(move.get(0), new ArrayList<>());

        return validDestinations.contains(move.get(1));
    }

    private static void play(final List<Coordinates> move) {
        board.play(move);
        toMove = toMove.reverseColor();
        printBoard();
    }

    private static List<Coordinates> parseMove(final String move) {
        final Coordinates source = new Coordinates(move.substring(0, 2));
        final Coordinates destination = new Coordinates(move.substring(2, 4));
        return Arrays.asList(source, destination);
    }

    private static Map<Coordinates, List<Coordinates>> getValidMoves(final Color color) {
        final Map<Coordinates, List<Coordinates>> moves = new HashMap<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                checkDestinationSquare(moves, color, x, y);
            }
        }

        return moves;
    }

    private static void checkDestinationSquare(final Map<Coordinates, List<Coordinates>> moves,
                                               final Color color, final int x, final int y) {

        final Coordinates source = new Coordinates(x, y);
        if (board.getSquare(x, y).getColor() == color) {
            for (final Coordinates destination : board.getValidPieceMoves(source)) {
                if (revealsCheck(source, destination)) continue;

                if (moves.containsKey(source)) moves.get(source).add(destination);
                else moves.put(source, new ArrayList<>(Collections.singletonList(destination)));
            }
        }

        if (moves.containsKey(source)) {
            STREAM.print(notation(source) + " -> ");
            for (final Coordinates destination : moves.get(source)) {
                STREAM.print(notation(destination) + " ");
            }

            STREAM.println();
        }
    }

    private static boolean revealsCheck(final Coordinates source, final Coordinates destination) {
        final Piece piece = board.getPiece(destination);
        board.play(Arrays.asList(source, destination));
        toMove = toMove.reverseColor();

        final boolean isValid = board.isInCheck(board.getColor(destination));
        board.undo(Arrays.asList(source, destination), piece);
        toMove = toMove.reverseColor();

        return isValid;
    }

    private static String notation(final Coordinates coordinates) {
        String notation = String.valueOf((char) (coordinates.getX() + 'a'));
        notation += (char) (coordinates.getY() + '1');
        return notation;
    }

    private static void initialise() {
        board = new Board();
        toMove = Color.WHITE;
        inProgress = true;
        printBoard();
    }

    private static void printBoard() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                STREAM.print(board.getSquare(x, y).getPiece().getLetter());
            }

            STREAM.println();
        }
    }

}
