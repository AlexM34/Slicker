import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    @Override
    public char getLetter() {
        return 'p';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates, final boolean isWhite) {
        final List<Coordinates> moves = new ArrayList<>();
        final int direction = isWhite ? 1 : -1;

        final Square singleMove = board.getShiftedSquare(coordinates, 0, direction);
        if (isValidDestination(singleMove, isWhite)) moves.add(singleMove.getCoordinates());

        final Square doubleMove = board.getShiftedSquare(coordinates, 0, 2 * direction);
        if (canDouble(coordinates, isWhite) &&
                isValidDestination(doubleMove, isWhite)) moves.add(doubleMove.getCoordinates());

        return moves;
    }

    private boolean canDouble(final Coordinates coordinates, final boolean isWhite) {
        return isWhite ? coordinates.getY() == 1 : coordinates.getY() == 6;
    }
}
