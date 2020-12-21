package setup;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    public Queen(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'Q' : 'q';
    }

    @Override
    public Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final Set<Coordinates> moves = new HashSet<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x != 0 || y != 0) moves.addAll(getSquares(board, coordinates, x, y));
            }
        }

        return moves;
    }

}
