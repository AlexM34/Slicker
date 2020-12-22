package setup;

import java.util.Arrays;
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

        if (board.isShortCastleAllowed(color) && !board.isInCheck(color)) {
            boolean valid = true;
            for (int x = 1; x <= 2; x++) {
                final Square square = board.getShiftedSquare(coordinates, x, 0);
                if (!square.isEmpty()) {
                    valid = false;
                    break;
                }

                board.play(Arrays.asList(coordinates, square.getCoordinates()));
                if (board.isInCheck(color)) {
                    board.undo(Arrays.asList(coordinates, square.getCoordinates()), new None());
                    valid = false;
                    break;
                }

                board.undo(Arrays.asList(coordinates, square.getCoordinates()), new None());

            }

            if (valid) {
                final Square castlePosition = board.getShiftedSquare(coordinates, 2, 0);
                moves.add(castlePosition.getCoordinates());
            }
        }

        if (board.isLongCastleAllowed(color) && !board.isInCheck(color)) {
            boolean valid = true;
            for (int x = -1; x >= -2; x--) {
                final Square square = board.getShiftedSquare(coordinates, x, 0);
                if (!square.isEmpty()) {
                    valid = false;
                    break;
                }

                board.play(Arrays.asList(coordinates, square.getCoordinates()));
                if (board.isInCheck(color)) {
                    board.undo(Arrays.asList(coordinates, square.getCoordinates()), new None());
                    valid = false;
                    break;
                }

                board.undo(Arrays.asList(coordinates, square.getCoordinates()), new None());

            }

            final Square square = board.getShiftedSquare(coordinates, -3, 0);
            if (square.isEmpty() && valid) {
                final Square castlePosition = board.getShiftedSquare(coordinates, -2, 0);
                moves.add(castlePosition.getCoordinates());
            }
        }

        return moves;
    }

}
