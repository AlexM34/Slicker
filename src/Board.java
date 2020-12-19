import java.util.List;

public class Board {

    private final Square[][] squares;

    Board() {
        squares = new Square[8][8];
        setup();
    }

    void play(final List<Coordinates> move) {
        squares[move.get(1).getX()][move.get(1).getY()].movePiece(squares[move.get(0).getX()][move.get(0).getY()]);
        squares[move.get(0).getX()][move.get(0).getY()].makeEmpty();
    }

    void undo(final List<Coordinates> move, final Piece piece) {
        squares[move.get(0).getX()][move.get(0).getY()].movePiece(squares[move.get(1).getX()][move.get(1).getY()]);

        if (piece != null) {
            final Color destinationColor = squares[move.get(1).getX()][move.get(1).getY()].getColor();
            final Color color = destinationColor.reverseColor();
            squares[move.get(1).getX()][move.get(1).getY()].movePiece(new Square(new Coordinates(0, 0), piece, color));

        } else {
            squares[move.get(1).getX()][move.get(1).getY()].makeEmpty();
        }
    }

    Square[][] getSquares() {
        return squares;
    }

    Square getSquare(final Coordinates coordinates) {
        return squares[coordinates.getX()][coordinates.getY()];
    }

    Color getColor(final Coordinates coordinates) {
        return squares[coordinates.getX()][coordinates.getY()].getColor();
    }

    Piece getPiece(final Coordinates coordinates) {
        return squares[coordinates.getX()][coordinates.getY()].getPiece();
    }

    Square getShiftedSquare(final Coordinates coordinates, final int xShift, final int yShift) {
        if (!coordinates.canShift(xShift, yShift)) return null;
        return squares[coordinates.getX() + xShift][coordinates.getY() + yShift];
    }

    private void setup() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                final Piece piece = findPiece(x, y);
                Color color = null;
                if (y < 2) color = Color.WHITE;
                else if (y > 5) color = Color.BLACK;
                squares[x][y] = new Square(new Coordinates(x, y), piece, color);
            }
        }
    }

    private Piece findPiece(final int x, final int y) {
        if (1 < y && y < 6) return null;
        else if (y == 1 || y == 6) return new Pawn();
        else if (x == 1 || x == 6) return new Knight();
        else if (x == 2 || x == 5) return new Bishop();
        else if (x == 0 || x == 7) return new Rook();
        else if (x == 3) return new Queen();
        else if (x == 4) return new King();
        throw new IllegalArgumentException(String.format("Coordinates (%d, %d) do not exist on the board", x, y));
    }

    public boolean isInCheck(final Color color) {
        final Coordinates king = findKing(color);
        final Color enemyColor = color.reverseColor();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (squares[x][y].getColor() == enemyColor && isValid(new Coordinates(x, y), king)) return true;
            }
        }

        return false;
    }

    private boolean isValid(final Coordinates source, final Coordinates destination) {
        return getPiece(source).getValidSquares(this, source, getColor(source)).contains(destination);
    }

    private Coordinates findKing(final Color color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (squares[x][y].getPiece() != null && squares[x][y].getPiece().getLetter() == 'k'
                        && squares[x][y].getColor() == color) {
                    return new Coordinates(x, y);
                }
            }
        }

        throw new IllegalStateException(String.format("The %s king is not present on the board!",
                (color.isWhite()? "white" : "black")));
    }

}
