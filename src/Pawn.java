import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    @Override
    public char getLetter() {
        return 'p';
    }

    @Override
    public List<int[]> getValidSquares(final Square[][] board, final int x, final int y, final boolean isWhite) {
        final List<int[]> moves = new ArrayList<>();
        final int direction = isWhite ? 1 : -1;

        if (isValidSquare(board, x, y + direction, isWhite)) moves.add(new int[]{x, y + direction});
        if (isValidSquare(board, x, y + 2 * direction, isWhite)) moves.add(new int[]{x, y + 2 * direction});
        return moves;
    }
}
