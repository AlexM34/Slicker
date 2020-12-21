package setup;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    public Rook(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'R' : 'r';
    }

    @Override
    public Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final Set<Coordinates> moves = new HashSet<>();
        for (int i = -1; i <= 1; i += 2) {
            moves.addAll(getSquares(board, coordinates, i, 0));
            moves.addAll(getSquares(board, coordinates, 0, i));
        }

        return moves;
    }

}
