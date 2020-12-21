package setup;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

    public Knight(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'N' : 'n';
    }

    @Override
    public Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final Set<Coordinates> moves = new HashSet<>();
        for (int x = -2; x <= 2; x++) {
            if (x == 0) continue;

            for (int y = -1; y <= 1; y += 2) {
                final Square square = board.getShiftedSquare(coordinates, x, y * (3 - Math.abs(x)));
                if (isValidDestination(square)) moves.add(square.getCoordinates());
            }
        }

        return moves;
    }

}
