package setup;

import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {

    private final Square[][] squares;
    private final Map<Integer, Coordinates> enPassant;
    private final Deque<List<Coordinates>> moves;
    public final Castling castling;

    public Board() {
        squares = new Square[8][8];
        enPassant = new HashMap<>();
        moves = new ArrayDeque<>();
        castling = new Castling();

        setup();
    }

    private void setup() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                final Color color;
                if (y < 2) color = Color.WHITE;
                else if (y > 5) color = Color.BLACK;
                else color = Color.EMPTY;
                final Piece piece = findPiece(x, y, color);

                squares[x][y] = new Square(new Coordinates(x, y), piece);
            }
        }
    }

    public void play(final String move) {
        final Coordinates source = new Coordinates(move.substring(0, 2));
        final Coordinates destination = new Coordinates(move.substring(2, 4));
        play(Arrays.asList(source, destination));
    }

    public void play(final List<Coordinates> move) {
        squares[move.get(1).getX()][move.get(1).getY()].setPiece(squares[move.get(0).getX()][move.get(0).getY()].getPiece());
        squares[move.get(0).getX()][move.get(0).getY()].makeEmpty();
        moves.addLast(move);
        castling.movePlayed(move, moves.size());

        completeCastling(move);
        checkForEnPassant(move);
    }

    private void completeCastling(final List<Coordinates> move) {
        if (squares[move.get(1).getX()][move.get(1).getY()].getPiece() instanceof King
                && Math.abs(move.get(0).getX() - move.get(1).getX()) == 2) {

            final int oldRookX = move.get(0).getX() > move.get(1).getX() ? 0 : 7;
            final int newRookX = (move.get(0).getX() + move.get(1).getX()) / 2;

            squares[newRookX][move.get(1).getY()].setPiece(squares[oldRookX][move.get(0).getY()].getPiece());
            squares[oldRookX][move.get(0).getY()].makeEmpty();
        }
    }

    private void checkForEnPassant(final List<Coordinates> move) {
        final Piece piece = squares[move.get(1).getX()][move.get(1).getY()].getPiece();
        final int sourceY = piece.getColor().isWhite() ? 1 : 6;
        final int destinationY = piece.getColor().isWhite() ? 3 : 4;

        if (piece instanceof Pawn && move.get(0).getY() == sourceY && move.get(1).getY() == destinationY) {
            final Coordinates enPassantSquare = new Coordinates(move.get(0).getX(), (sourceY + destinationY) / 2);
            enPassant.put(moves.size(), enPassantSquare);
        }
    }

    public void undo(final String move) {
        final Coordinates source = new Coordinates(move.substring(0, 2));
        final Coordinates destination = new Coordinates(move.substring(2, 4));
        undo(Arrays.asList(source, destination), new None());
    }

    public void undo(final List<Coordinates> move, final Piece piece) {
        undoCastling(move);
        squares[move.get(0).getX()][move.get(0).getY()].setPiece(squares[move.get(1).getX()][move.get(1).getY()].getPiece());
        squares[move.get(1).getX()][move.get(1).getY()].setPiece(piece);
        castling.moveUndone(moves.size());
        moves.removeLast();

        enPassant.remove(moves.size());
    }

    private void undoCastling(final List<Coordinates> move) {
        if (squares[move.get(1).getX()][move.get(1).getY()].getPiece() instanceof King
                && Math.abs(move.get(0).getX() - move.get(1).getX()) == 2) {

            final int previousRookX = move.get(0).getX() > move.get(1).getX() ? 0 : 7;
            final int currentRookX = (move.get(0).getX() + move.get(1).getX()) / 2;

            squares[previousRookX][move.get(0).getY()].setPiece(squares[currentRookX][move.get(1).getY()].getPiece());
            squares[currentRookX][move.get(1).getY()].makeEmpty();
        }
    }

    public boolean isShortCastleAllowed(final Color color) {
        final int shortCastling = color.isWhite() ? castling.getWhiteShortCastle() : castling.getBlackShortCastle();

        return isCastlingAllowed(shortCastling);
    }

    public boolean isLongCastleAllowed(final Color color) {
        final int longCastling = color.isWhite() ? castling.getWhiteLongCastle() : castling.getBlackLongCastle();

        return isCastlingAllowed(longCastling);
    }

    private boolean isCastlingAllowed(final int castling) {
        return moves.size() < castling;
    }

    boolean isEnPassantAllowed(final Square square) {
        return square.getCoordinates().equals(enPassant.get(moves.size()));
    }

    public Square getSquare(final String square) {
        return getSquare(square.charAt(0) - 'a', square.charAt(1) - '1');
    }

    public Square getSquare(final int x, final int y) {
        return squares[x][y];
    }

    public Color getColor(final Coordinates coordinates) {
        return squares[coordinates.getX()][coordinates.getY()].getColor();
    }

    public Piece getPiece(final Coordinates coordinates) {
        return squares[coordinates.getX()][coordinates.getY()].getPiece();
    }

    Square getShiftedSquare(final Coordinates coordinates, final int xShift, final int yShift) {
        if (!coordinates.canShift(xShift, yShift)) return new Square();
        return squares[coordinates.getX() + xShift][coordinates.getY() + yShift];
    }

    public void printMoves(final PrintStream stream) {
        moves.forEach(stream::println);
    }

    private Piece findPiece(final int x, final int y, final Color color) {
        if (1 < y && y < 6) return new None();
        else if (y == 1 || y == 6) return new Pawn(color);
        else if (x == 1 || x == 6) return new Knight(color);
        else if (x == 2 || x == 5) return new Bishop(color);
        else if (x == 0 || x == 7) return new Rook(color);
        else if (x == 3) return new Queen(color);
        else if (x == 4) return new King(color);

        throw new IllegalArgumentException(String.format("piece.Coordinates (%d, %d) do not exist on the board", x, y));
    }

    public boolean isInCheck(final Color color) {
        final Coordinates king = findKing(color);
        final Color enemyColor = color.reverseColor();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (squares[x][y].getColor().equals(enemyColor) && !(squares[x][y].getPiece() instanceof King)
                        && isValid(new Coordinates(x, y), king)) return true;
            }
        }

        return false;
    }

    private boolean isValid(final Coordinates source, final Coordinates destination) {
        return getPiece(source).getValidSquares(this, source).contains(destination);
    }

    private Coordinates findKing(final Color color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (squares[x][y].getPiece() instanceof King && squares[x][y].getColor().equals(color)) {
                    return new Coordinates(x, y);
                }
            }
        }

        throw new IllegalStateException(String.format("The %s king is not present on the board!",
                (color.isWhite() ? "white" : "black")));
    }

    public Set<Coordinates> getValidPieceMoves(final Coordinates source) {
        return getPiece(source).getValidSquares(this, source);
    }

}
