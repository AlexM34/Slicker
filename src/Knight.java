import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    @Override
    public char getLetter() {
        return 'n';
    }

    @Override
    public List<int[]> getValidSquares(final Square[][] board, final int x, final int y, final boolean isWhite) {
        final List<int[]> moves = new ArrayList<>();
        for (int x0 = -2; x0 <= 2; x0++) {
            if (x0 == 0) continue;
            if (isValidSquare(board, x + x0, y + 3 - Math.abs(x0), isWhite)) {
                moves.add(new int[]{x + x0, y + 3 - Math.abs(x0)});
            }

            if (isValidSquare(board, x + x0, y - 3 + Math.abs(x0), isWhite)) {
                moves.add(new int[]{x + x0, y - 3 + Math.abs(x0)});
            }
        }

        return moves;
    }
}
