package setup;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        this.color = color;
    }

    @Override
    public char getLetter() {
        return color.isWhite() ? 'P' : 'p';
    }

    @Override
    public Set<Coordinates> getValidSquares(final Board board, final Coordinates coordinates) {
        final Set<Coordinates> moves = new HashSet<>();
        final int direction = color.isWhite() ? 1 : -1;

        final Square singleMove = board.getShiftedSquare(coordinates, 0, direction);
        if (isValidDestination(singleMove) && singleMove.isEmpty()) {
            moves.add(singleMove.getCoordinates());

            final Square doubleMove = board.getShiftedSquare(coordinates, 0, 2 * direction);
            if (canDouble(coordinates) && isValidDestination(doubleMove) &&
                    doubleMove.isEmpty()) moves.add(doubleMove.getCoordinates());
        }

        for (int x = -1; x <= 1; x += 2) {
            final Square captureMove = board.getShiftedSquare(coordinates, x, direction);
            if (isValidDestination(captureMove) &&
                    (!captureMove.isEmpty() || board.isEnPassant(captureMove))) moves.add(captureMove.getCoordinates());
        }

        return moves;
    }

    private boolean canDouble(final Coordinates coordinates) {
        return color.isWhite() ? coordinates.getY() == 1 : coordinates.getY() == 6;
    }

}
