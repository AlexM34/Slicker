import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    @Override
    public char getLetter() {
        return 'r';
    }

    @Override
    public List<int[]> getValidSquares(final Square[][] board, final int x, final int y, final boolean isWhite) {
        final List<int[]> moves = new ArrayList<>();
        for (int i = -1; i >= -7; i--) {
            if (!isValidSquare(board, x + i, y, isWhite)) break;
            moves.add(new int[]{x + i, y});
        }

        for (int i = -1; i >= -7; i--) {
            if (!isValidSquare(board, x, y + i, isWhite)) break;
            moves.add(new int[]{x, y + i});
        }

        for (int i = 1; i <= 7; i++) {
            if (!isValidSquare(board, x + i, y, isWhite)) break;
            moves.add(new int[]{x + i, y});
        }

        for (int i = 1; i <= 7; i++) {
            if (!isValidSquare(board, x, y + i, isWhite)) break;
            moves.add(new int[]{x, y + i});
        }
        return moves;
    }
}
