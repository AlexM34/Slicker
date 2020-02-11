import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    @Override
    public char getLetter() {
        return 'b';
    }

    @Override
    public List<int[]> getValidSquares(final Square[][] board, final int x, final int y, final boolean isWhite) {
        final List<int[]> moves = new ArrayList<>();
        for (int i = -1; i >= -7; i--) {
            if (!isValidSquare(board, x + i, y + i, isWhite)) break;
            moves.add(new int[]{x + i, y + i});
        }

        for (int i = -1; i >= -7; i--) {
            if (!isValidSquare(board, x + i, y - i, isWhite)) break;
            moves.add(new int[]{x + i, y - i});
        }

        for (int i = 1; i <= 7; i++) {
            if (!isValidSquare(board, x + i, y + i, isWhite)) break;
            moves.add(new int[]{x + i, y + i});
        }

        for (int i = 1; i <= 7; i++) {
            if (!isValidSquare(board, x + i, y - i, isWhite)) break;
            moves.add(new int[]{x + i, y - i});
        }
        return moves;
    }
}
