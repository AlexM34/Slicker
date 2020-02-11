import java.util.List;

public abstract class Piece {
    abstract char getLetter();

    abstract List<int[]> getValidSquares(final Square[][] board, final int x, final int y, final boolean isWhite);

    boolean isValidSquare(final Square[][] board, final int x, final int y, final boolean isWhite) {
        return 0 <= x && x < 8 && 0 <= y && y < 8 && board[x][y].getColor() != Boolean.valueOf(isWhite);
    }
}
