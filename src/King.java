import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    @Override
    public char getLetter() {
        return 'k';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates, final Color color) {
        final List<Coordinates> moves = new ArrayList<>();
        for (int x0 = -1; x0 <= 1; x0++) {
            for (int y0 = -1; y0 <= 1; y0++) {
                if (x0 == 0 && y0 == 0) continue;

                final Square destination = board.getShiftedSquare(coordinates, x0, y0);
                if (isValidDestination(destination, color)) moves.add(destination.getCoordinates());
            }
        }

        return moves;
    }
}
