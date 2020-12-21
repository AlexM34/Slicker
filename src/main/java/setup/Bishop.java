package setup;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'B' : 'b';
    }

    @Override
    public Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final Set<Coordinates> moves = new HashSet<>();
        for (int i = -1; i <= 1; i += 2) {
            moves.addAll(getSquares(board, coordinates, i, -i));
            moves.addAll(getSquares(board, coordinates, i, i));
        }

        return moves;
    }

}
