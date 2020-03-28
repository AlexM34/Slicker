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
        if (isValidDestination(singleMove, isWhite) && singleMove.isEmpty()) {
            moves.add(singleMove.getCoordinates());

            final Square doubleMove = board.getShiftedSquare(coordinates, 0, 2 * direction);
            if (canDouble(coordinates, isWhite) && isValidDestination(doubleMove, isWhite) &&
                    doubleMove.isEmpty()) moves.add(doubleMove.getCoordinates());
        }

        final Square takeLeft = board.getShiftedSquare(coordinates, -1, direction);
        if (isValidDestination(takeLeft, isWhite) && !takeLeft.isEmpty()) moves.add(takeLeft.getCoordinates());

        final Square takeRight = board.getShiftedSquare(coordinates, 1, direction);
        if (isValidDestination(takeRight, isWhite) && !takeRight.isEmpty()) moves.add(takeRight.getCoordinates());

        return moves;
    }

    private boolean canDouble(final Coordinates coordinates, final boolean isWhite) {
        return isWhite ? coordinates.getY() == 1 : coordinates.getY() == 6;
    }
}
