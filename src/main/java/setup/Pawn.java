package setup;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'P' : 'p';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final List<Coordinates> moves = new ArrayList<>();
        final int direction = color.isWhite() ? 1 : -1;

        final Square singleMove = board.getShiftedSquare(coordinates, 0, direction);
        if (isValidDestination(singleMove, color) && singleMove.isEmpty()) {
            moves.add(singleMove.getCoordinates());

            final Square doubleMove = board.getShiftedSquare(coordinates, 0, 2 * direction);
            if (canDouble(coordinates, color) && isValidDestination(doubleMove, color) &&
                    doubleMove.isEmpty()) moves.add(doubleMove.getCoordinates());
        }

        final Square takeLeft = board.getShiftedSquare(coordinates, -1, direction);
        if (isValidDestination(takeLeft, color) && !takeLeft.isEmpty()) moves.add(takeLeft.getCoordinates());

        final Square takeRight = board.getShiftedSquare(coordinates, 1, direction);
        if (isValidDestination(takeRight, color) && !takeRight.isEmpty()) moves.add(takeRight.getCoordinates());

        return moves;
    }

    private boolean canDouble(final Coordinates coordinates, final Color color) {
        return color.isWhite() ? coordinates.getY() == 1 : coordinates.getY() == 6;
    }

}
