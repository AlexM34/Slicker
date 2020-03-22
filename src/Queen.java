import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    @Override
    public char getLetter() {
        return 'q';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates, final boolean isWhite) {
        final List<Coordinates> moves = new ArrayList<>();
        for (int i = -1; i >= -7; i--) {
            final Square destination = board.getShiftedSquare(coordinates, i, 0);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        for (int i = -1; i >= -7; i--) {
            final Square destination = board.getShiftedSquare(coordinates, 0, i);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        for (int i = 1; i <= 7; i++) {
            final Square destination = board.getShiftedSquare(coordinates, i, 0);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        for (int i = 1; i <= 7; i++) {
            final Square destination = board.getShiftedSquare(coordinates, 0, i);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        for (int i = -1; i >= -7; i--) {
            final Square destination = board.getShiftedSquare(coordinates, i, i);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        for (int i = -1; i >= -7; i--) {
            final Square destination = board.getShiftedSquare(coordinates, i, -i);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        for (int i = 1; i <= 7; i++) {
            final Square destination = board.getShiftedSquare(coordinates, i, i);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        for (int i = 1; i <= 7; i++) {
            final Square destination = board.getShiftedSquare(coordinates, i, -i);
            if (!isValidDestination(destination, isWhite)) break;
            moves.add(destination.getCoordinates());

            if (!isValidDestination(destination, !isWhite)) break;
        }

        return moves;
    }
}
