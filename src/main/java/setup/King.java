package setup;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {

    public King(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'K' : 'k';
    }

    @Override
    public Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final Set<Coordinates> moves = new HashSet<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;

                final Square destination = board.getShiftedSquare(coordinates, x, y);
                if (isValidDestination(destination)) moves.add(destination.getCoordinates());
            }
        }

        return moves;
    }

}
