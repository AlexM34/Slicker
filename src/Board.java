public class Board {
    private final Square[][] board;

    public Board() {
        this.board = new Square[8][8];
        setup();
    }

    public Square[][] getBoard() {
        return board;
    }

    private void setup() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                final Piece piece = findPiece(x, y);
                Boolean color = null;
                if (y < 2) color = true;
                else if (y > 5) color = false;
                this.board[x][y] = new Square(x, y, piece, color);
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
}
