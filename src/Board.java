import java.util.List;

public class Board {
    private static Square[][] board = new Square[8][8];

    Board() {
        board = new Square[8][8];
        setup();
    }

    static void play(final List<Coordinates> move) {
        board[move.get(1).getX()][move.get(1).getY()].movePiece(board[move.get(0).getX()][move.get(0).getY()]);
        board[move.get(0).getX()][move.get(0).getY()].makeEmpty();
    }

    Square[][] getBoard() {
        return board;
    }

    Square getSquare(final Coordinates coordinates) {
        return board[coordinates.getX()][coordinates.getY()];
    }

    boolean getColor(final Coordinates coordinates) {
        return board[coordinates.getX()][coordinates.getY()].getColor();
    }

    Piece getPiece(final Coordinates coordinates) {
        return board[coordinates.getX()][coordinates.getY()].getPiece();
    }

    Square getShiftedSquare(final Coordinates coordinates, final int xShift, final int yShift) {
        if (!coordinates.canShift(xShift, yShift)) return null;
        return board[coordinates.getX() + xShift][coordinates.getY() + yShift];
    }

    private void setup() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                final Piece piece = findPiece(x, y);
                Boolean color = null;
                if (y < 2) color = true;
                else if (y > 5) color = false;
                board[x][y] = new Square(new Coordinates(x, y), piece, color);
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

    private boolean isInCheck(final boolean isWhite) {
        final Coordinates king = findKing(isWhite);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y].getColor() == !isWhite && isValid(new Coordinates(x, y), king)) return true;
            }
        }

        return false;
    }

    private boolean isValid(final Coordinates source, final Coordinates destination) {
        return getPiece(source).getValidSquares(this, destination, getColor(source)).contains(destination);
    }

    private Coordinates findKing(final boolean isWhite) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y].getPiece().getLetter() == 'k' && board[x][y].getColor() == isWhite) {
                    return new Coordinates(x, y);
                }
            }
        }

        throw new IllegalStateException(String.format("The %s king is not present on the board!",
                (isWhite ? "white" : "black")));
    }
}
