import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    @Override
    public char getLetter() {
        return 'n';
    }

    @Override
    public List<Coordinates> getValidSquares(final Board board, final Coordinates coordinates, final boolean isWhite) {
        final List<Coordinates> moves = new ArrayList<>();
        for (int x0 = -2; x0 <= 2; x0++) {
            if (x0 == 0) continue;

            final Square goUp = board.getShiftedSquare(coordinates, x0, 3 - Math.abs(x0));
            if (isValidDestination(goUp, isWhite)) moves.add(goUp.getCoordinates());

            final Square goDown = board.getShiftedSquare(coordinates, x0, Math.abs(x0) - 3);
            if (isValidDestination(goDown, isWhite)) moves.add(goDown.getCoordinates());
        }

        return moves;
    }
}
