import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    @Override
    public char getLetter() {
        return 'b';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates, final Color color) {
        final List<Coordinates> moves = new ArrayList<>();
        for (int i = -1; i >= -7; i--) {
            final Square destination = board.getShiftedSquare(coordinates, i, i);
            if (!isValidDestination(destination, color)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, color.reverseColor())) break;
        }

        for (int i = -1; i >= -7; i--) {
            final Square destination = board.getShiftedSquare(coordinates, i, -i);
            if (!isValidDestination(destination, color)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, color.reverseColor())) break;
        }

        for (int i = 1; i <= 7; i++) {
            final Square destination = board.getShiftedSquare(coordinates, i, i);
            if (!isValidDestination(destination, color)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, color.reverseColor())) break;
        }

        for (int i = 1; i <= 7; i++) {
            final Square destination = board.getShiftedSquare(coordinates, i, -i);
            if (!isValidDestination(destination, color)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, color.reverseColor())) break;
        }

        return moves;
    }
}
