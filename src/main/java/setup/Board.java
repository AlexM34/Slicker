package setup;

import java.util.Arrays;
import java.util.List;

public class Board {

    private final Square[][] squares;

    public Board() {
        squares = new Square[8][8];
        setup();
    }

    public void play(final String move) {
        final Coordinates source = transformCoordinates(move.substring(0, 2));
        final Coordinates destination = transformCoordinates(move.substring(2, 4));
        play(Arrays.asList(source, destination));
    }

    private static Coordinates transformCoordinates(final String s) {
        return new Coordinates(s.charAt(0) - 'a', s.charAt(1) - '1');
    }

    public void play(final List<Coordinates> move) {
        squares[move.get(1).getX()][move.get(1).getY()].setPiece(squares[move.get(0).getX()][move.get(0).getY()].getPiece());
        squares[move.get(0).getX()][move.get(0).getY()].makeEmpty();
    }

    public void undo(final List<Coordinates> move, final Piece piece) {
        squares[move.get(0).getX()][move.get(0).getY()].setPiece(squares[move.get(1).getX()][move.get(1).getY()].getPiece());
        squares[move.get(1).getX()][move.get(1).getY()].setPiece(piece);
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

    private Piece findPiece(final int x, final int y, final Color color) {
        if (1 < y && y < 6) return new None(color);
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
                if (squares[x][y].getColor().equals(enemyColor) && isValid(new Coordinates(x, y), king)) return true;
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

    public List<Coordinates> getValidPieceMoves(final Coordinates source) {
        return getPiece(source).getValidSquares(this, source);
    }

}