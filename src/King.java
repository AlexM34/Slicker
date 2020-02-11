import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    @Override
    public char getLetter() {
        return 'k';
    }

    @Override
    public List<int[]> getValidSquares(final Square[][] board, final int x, final int y, final boolean isWhite) {
        final List<int[]> moves = new ArrayList<>();
        for (int x0 = -1; x0 <= 1; x0++) {
            for (int y0 = -1; y0 <= 1; y0++) {
                if (x0 == 0 && y0 == 0) continue;
                if (isValidSquare(board, x + x0, y + y0, isWhite)) moves.add(new int[]{x + x0, y + y0});
            }
        }

        return moves;
    }
}
